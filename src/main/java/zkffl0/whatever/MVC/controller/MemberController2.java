package zkffl0.whatever.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import zkffl0.whatever.MVC.domain.member.Member2;
import zkffl0.whatever.MVC.domain.member.MemberForm;
import zkffl0.whatever.MVC.service.MemberService2;

import java.util.List;

@Controller
public class MemberController2 {

    private final MemberService2 memberService;

    @Autowired
    public MemberController2(MemberService2 memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member2 member = new Member2();
        member.setName(form.getName());

        try {
            memberService.join(member);
        } catch (IllegalStateException e) {
            if (e.getMessage().equals("이미 존재하는 회원입니다.")) {
                return "members/validateDuplicateMember";
            } else if (e.getMessage().equals("이름이 입력되지 않았습니다.")) {
                return "members/emptyMemberName";
            }
        }

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member2> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
