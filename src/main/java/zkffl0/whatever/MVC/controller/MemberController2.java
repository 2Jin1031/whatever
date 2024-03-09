package zkffl0.whatever.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import zkffl0.whatever.MVC.domain.Member2;
import zkffl0.whatever.MVC.domain.MemberForm;
import zkffl0.whatever.MVC.service.MemberService2;

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

        memberService.join(member);

        return "redirect:/";
    }
}
