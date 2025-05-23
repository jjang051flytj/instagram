package com.jjang051.instagram.service;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dao.MemberDao;
import com.jjang051.instagram.dto.MemberDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.mapper.MemberMapper;
import com.jjang051.instagram.repository.MemberRepository;
import com.jjang051.instagram.utils.FileRenameStrategy;
import com.jjang051.instagram.utils.UUIDFileRenameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    //repository 를 직접 써도 된다.....
    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberMapper memberMapper; // MapStruct Mapper 주입

    @Value("${file.path}")
    String upload;

    public Member save(MemberDto memberDto) {
        String encodedPassword =  bCryptPasswordEncoder.encode(memberDto.getUserPW());
        memberDto.setUserPW(encodedPassword);
        memberDto.setRole(Role.ROLE_USER);
        //Member savedMember = memberDto.toMember();
        Member savedMember = memberMapper.toEntity(memberDto);
        return memberDao.save(savedMember);
    }
    public List<MemberDto> findAll() {
        return memberDao.findAll().stream()
                //.filter(member -> !member.getRole().name().equals("ROLE_ADMIN"))
                .filter(member -> member.getRole()!=Role.ROLE_ADMIN)
                .map(member->
                        MemberDto.builder()
                                .userID(member.getUserID())
                                .userName(member.getUserName())
                                .userEmail(member.getUserEmail())
                                .regDate(member.getRegDate())
                                .modifyDate(member.getModifyDate())
                                .role(member.getRole())
                        .build()
                ).toList();
    }

    public int deleteByUserID(String userID) {
        return memberDao.deleteByUserID(userID);
    }

    public Member findByUserID(String userID) {
        Optional<Member> optionalMember = memberDao.findByUserID(userID);
        if(optionalMember.isPresent()){
            return optionalMember.get();
        }
        return null;
    }

    @Transactional
    public Member chageProfile(String loggedMemberID, MultipartFile profileImg) {
        //이미지 업로드하고 db에 profileImg 갱신해줘야 한다.
        String originalFileName = profileImg.getOriginalFilename();
        if (!originalFileName.isBlank()) {
            FileRenameStrategy fileRenameStrategy = new UUIDFileRenameStrategy();
            String renamedFileName = fileRenameStrategy.renameFile(originalFileName);
            Path path = Paths.get(upload,renamedFileName);
            //db에 update하기...
            //jpa update save();
            try {
                Files.write(path,profileImg.getBytes());
                Optional<Member> optionalMember = memberDao.findByUserID(loggedMemberID);
                if(optionalMember.isPresent()){
                    Member member = optionalMember.get();
                    return member.updateProfileImg(renamedFileName);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
