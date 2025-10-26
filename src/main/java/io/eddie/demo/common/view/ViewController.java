package io.eddie.demo.common.view;

import io.eddie.demo.domain.carts.domain.model.CartMapper;
import io.eddie.demo.domain.carts.domain.model.dto.CartDescription;
import io.eddie.demo.domain.carts.domain.model.entity.Cart;
import io.eddie.demo.domain.carts.application.port.in.CartUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class ViewController {

    private final CartUseCase cartUseCase;

    @GetMapping("/carts/{accountCode}")
    public String viewCartDescriptionPage(
            @PathVariable String accountCode,
            Model model
    ) {

        Cart cart = cartUseCase.getByAccountCode(accountCode);
        model.addAttribute("cart", cart);

        return "carts/cart";
    }

    @GetMapping("/payments/checkout/{accountCode}")
    public String viewCheckoutPage(
            @PathVariable String accountCode,
            Model model
    ) {

        // 장바구니 아이템 코드가 다 넘어왓다는 가정

        Cart cart = cartUseCase.getByAccountCode(accountCode);
        CartDescription description = CartMapper.toCartDescription(cart);

        model.addAttribute("description", description);
//        model.addAttribute("accountCode", accountCode);

        return "payments/checkout";

    }

    @GetMapping("/payments/success")
    public String viewSuccessPage() {
        return "payments/success";
    }

    @GetMapping("/payments/fail")
    public String viewFailurePage() {
        return "payments/fail";
    }

}
