package cn.shiying.drugs_purchase.client;

import cn.shiying.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/activiti")
@FeignClient("shiying-service-activiti")
public interface ActivitiClient {

    @PostMapping("/startDrug")
    Result startDrug(@RequestParam("purchaseId") String purchaseId);

    @GetMapping("/order")
    Result order();

    @PostMapping("/sub")
    Result sub(@RequestBody List<String> processInstanceIds);

    @GetMapping("/checkOrder")
    Result checkOrder();

    @GetMapping("/warehouseCheck")
    Result warehouseCheck();

    @GetMapping("/reject")
    Result reject(@RequestParam("processInstanceIds") List<String> processInstanceIds,@RequestParam("reason") String reason);

    @PostMapping("/agree")
    Result agree(@RequestBody List<String> processInstanceIds);

    @PostMapping("/bpmName")
    Result bpmName(@RequestParam("processInstanceIds") List<String> processInstanceIds);
}
