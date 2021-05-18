package springboot.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.practice.domain.Member;
import springboot.practice.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 会員登録
     * @param member
     * @return
     */
    @Transactional(readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 重複チェック
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("既に存在している会員です。");
        }
    }

    // 会員全体照会
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 会員照会(1件)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
