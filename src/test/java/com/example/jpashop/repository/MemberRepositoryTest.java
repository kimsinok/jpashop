package com.example.jpashop.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.jpashop.domain.Address;
import com.example.jpashop.domain.Member;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
@Slf4j
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void test() {
        assertNotNull(memberRepository);
    }

    @Test
    @Rollback(false)
    public void testSave() {

        // given
        Member member = new Member();
        member.setName("Bob");

        // Address address = new Address();
        // address.setCity("부산");
        // address.setStreet("역삼로");
        // address.setZipcode("2222");
        // member.setAddress(address);

        member.setAddress(new Address("제주", "연동로", "11111"));

        // when
        memberRepository.save(member);

        Member foundMember = memberRepository.findById(member.getId());

        // then
        assertEquals(foundMember.getId(), member.getId());

        assertEquals(foundMember.getName(), member.getName());

    }

    @Test
    public void testFindAll() {

        // given
        // when

        List<Member> members = memberRepository.findAll();

        // then
        assertTrue(members.size() > 0);

        members.forEach(member -> {
            log.info("id : {}, name: {}, city : {}", member.getId(), member.getName(), member.getAddress().getCity());

        });
    }

}
