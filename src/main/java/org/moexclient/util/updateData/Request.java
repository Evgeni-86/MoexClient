package org.moexclient.util.updateData;


import org.moexclient.entity.Contract;
import org.moexclient.entity.ContractOpenPositions;
import org.moexclient.model.ResponseModel;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class Request {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String urlRequest = "https://www.moex.com/api/contract/OpenOptionService";
    private final DateTimeFormatter dateFormatMoexApi = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public ContractOpenPositions doRequest(Contract contract, LocalDate date) {

        String requestUrl =
                String.format("%s/%s/F/%s/json", urlRequest, dateFormatMoexApi.format(date), contract.getContract_name());

        ResponseModel[] forObject = restTemplate.getForObject(requestUrl, ResponseModel[].class);

        if (forObject != null && forObject.length == 4) {
            ResponseModel firstElement = forObject[0];
//            System.out.println("forObject.length " + forObject.length);
//            System.out.println("firstElement " + firstElement);
            return new ContractOpenPositions(date, contract.getId(), firstElement.getJuridicalLong(),
                    firstElement.getJuridicalShort(), firstElement.getPhysicalLong(), firstElement.getPhysicalShort());
        }

        return null;
    }
}
