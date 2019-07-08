package ca.jrvs.apps.twitter.example;

import ca.jrvs.apps.twitter.example.dto.Company;
import ca.jrvs.apps.twitter.example.dto.Financial;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static final String str = "{\n" +
            "   \"symbol\":\"AAPL\",\n" +
            "   \"companyName\":\"Apple Inc.\",\n" +
            "   \"exchange\":\"Nasdaq Global Select\",\n" +
            "   \"description\":\"Apple Inc is designs, manufactures and markets mobile communication and media devices and personal computers, and sells a variety of related software, services, accessories, networking solutions and third-party digital content and applications.\",\n" +
            "   \"CEO\":\"Timothy D. Cook\",\n" +
            "   \"sector\":\"Technology\",\n" +
            "   \"financials\":[\n" +
            "      {\n" +
            "         \"reportDate\":\"2018-12-31\",\n" +
            "         \"grossProfit\":320,\n" +
            "         \"costOfRevenue\":5227,\n" +
            "         \"operatingRevenue\":8431,\n" +
            "         \"totalRevenue\":8431,\n" +
            "         \"operatingIncome\":23346,\n" +
            "         \"netIncome\":1996\n" +
            "      },\n" +
            "      {\n" +
            "         \"reportDate\":\"2018-09-30\",\n" +
            "         \"grossProfit\":24084,\n" +
            "         \"costOfRevenue\":38816,\n" +
            "         \"operatingRevenue\":6290,\n" +
            "         \"totalRevenue\":629,\n" +
            "         \"operatingIncome\":1611,\n" +
            "         \"netIncome\":14125" +
            "\n" +
            "      }\n" +
            "   ],\n" +
            "   \"dividends\":[\n" +
            "      {\n" +
            "         \"exDate\":\"2018-02-09\",\n" +
            "         \"paymentDate\":\"2018-02-15\",\n" +
            "         \"recordDate\":\"2018-02-12\",\n" +
            "         \"declaredDate\":\"2018-02-01\",\n" +
            "         \"amount\":0.63\n" +
            "      },\n" +
            "      {\n" +
            "         \"exDate\":\"2017-11-10\",\n" +
            "         \"paymentDate\":\"2017-11-16\",\n" +
            "         \"recordDate\":\"2017-11-13\",\n" +
            "         \"declaredDate\":\"2017-11-02\",\n" +
            "         \"amount\":0.63\n" +
            "      }\n" +
            "   ]\n" +
            "}";

    /**
     * Convert a java object to JSON string
     *
     * @param object input object
     * @return JSON String
     * @throws JsonProcessingException
     */
    public static String toJson(Object object) throws
            JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper.writeValueAsString(object);


    }


    /**
     * Parse JSON string to a object
     *
     * @param json  JSON str
     * @param clazz object class
     * @param <T>   Type
     * @return Object
     * @throws IOException
     */
    public static <T> T toObjectFromJson(String json,
                                         Class clazz) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) mapper.readValue(json, clazz);


    }

    public static void main(String[] args) throws IOException {
        Company myCompany = new Company();
        Financial myFina = new Financial();
        List<Financial> financials = new ArrayList<>();
        myCompany.setCompanyName("Jarvis");
        myCompany.setExchange("Stock_MArket");
        myFina.setCostOfRevenue(1234);
        myFina.setTotalRevenue(12222);
        financials.add(myFina);
        myCompany.setFinancials(financials);

        Company newComp = toObjectFromJson(str, Company.class);

        System.out.println(toJson(newComp));
        System.out.println(toJson(myCompany));
    }
}
