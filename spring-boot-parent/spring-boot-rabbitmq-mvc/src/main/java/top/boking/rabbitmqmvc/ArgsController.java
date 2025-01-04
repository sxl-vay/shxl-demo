package top.boking.rabbitmqmvc;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.boking.rabbitmqmvc.util.ArgHolder;

@RestController
@RequestMapping("/args")
@Slf4j
public class ArgsController {

    @RequestMapping("/set")
    public String set(HttpServletRequest request) {
        String method = request.getMethod();
        String multiple = request.getParameter("multiple");
        double i = Double.parseDouble(multiple);
        ArgHolder.setMultiple(i);
        return "success";
    }
}
