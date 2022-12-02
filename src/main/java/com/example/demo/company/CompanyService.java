package com.example.demo.company;

import com.example.demo.parser.RequestService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final RequestService requestService = new RequestService();

    @Autowired
    public CompanyService(CompanyRepository studentRepository) {
        this.companyRepository = studentRepository;
    }

    public List<Optional<Company>> getCompany(String company_link){
        Optional<Company> companyOptional = companyRepository.findCompanyByLink(company_link);
        if (companyOptional.isPresent()){
            return List.of(companyRepository.findCompanyByLink(company_link));
        }
        Object res = requestService.getData(company_link);
        if (res == null){
            addNewCompany(new Company(company_link, company_link, "wrong request", "wrong request", "wrong request",
                    "wrong request", -1, "wrong request"));
            return List.of(companyRepository.findCompanyByLink(company_link));
        }
        String name = ((JSONObject) res).getString("name");
        String address = (((JSONObject) res).getString("address"));
        String icon  = (((JSONObject) res).getString("icon"));
        String logo = (((JSONObject) res).getString("logo"));
        String twitter = "doesn`t exist";
        String facebook = "doesn`t exist";
        for(Object i: ((JSONObject) res).getJSONArray("links")) {
            JSONObject jsonLineItem = (JSONObject) i;
            String key = jsonLineItem.getString("name");
            if (key.equals("facebook"))
                facebook = jsonLineItem.getString("url") ;
            else if (key.equals("twitter")) {
                twitter = jsonLineItem.getString("url");
            }
        }
        Integer noe = (((JSONObject) res).getInt("employees"));
        Company new_company = new Company(company_link, name, twitter, facebook, logo, icon, noe, address);
        addNewCompany(new_company);
        return List.of(companyRepository.findCompanyByLink(company_link));
    }

    public void addNewCompany(Company company) {
        Optional<Company>  studentOptional=  companyRepository.findCompanyByLink(company.getLink());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Company have already exist");
        }
        companyRepository.save(company);

    }
}
