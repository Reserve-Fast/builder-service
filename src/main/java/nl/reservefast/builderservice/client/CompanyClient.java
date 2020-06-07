package nl.reservefast.builderservice.client;

import nl.reservefast.builderservice.model.Company;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("company-service")
@RibbonClient("company-service")
public interface CompanyClient {

    @GetMapping("/company/{id}")
    Company getCompany(@PathVariable String id);

}
