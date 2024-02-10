public class BlockMemberService {
    private MemberRepository memberRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public void block(String memberId){
        Member member = memberRepository.findById(memberId);
        if(member == null) throw new NoMemberException();
        member.block();
    }
}
