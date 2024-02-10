public class ChangePasswordService {
    @Transactional
    public void changePassword(ChangePasswordRequest req){
        Member member = findExistiongMember(req.getMemberId());
        checkMemberExists(member);
        member.changePassword(req.getCurrentPassword(), req.getNewPassword());
    }
}
