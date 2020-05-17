package cn.shiying.drugs_purchase.client;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/drugs_storage/storage")
@FeignClient("shiying-service-drugs-storage")
public interface DrugsStorageClient {
    @PostMapping("/save")
    public Result save(@RequestBody List<DrugsPurchaseDetailed> list);
}
