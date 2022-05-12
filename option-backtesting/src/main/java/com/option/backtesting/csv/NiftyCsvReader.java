package com.option.backtesting.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.option.backtesting.pojo.OptionIDX;
import com.option.backtesting.pojo.OptionPcrRation;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class NiftyCsvReader {

    private String expression = "OPTIDX_NIFTY";
//
//    @Autowired
//    private ResourceLoader resourceLoader;

    public List<OptionIDX> readCsv() throws IOException {
        //check if any files exists in resources folder
        final List<OptionIDX> ceOptionsIDX = getCEOptionsIDX();
        final List<OptionIDX> peOptionsIDX = getPEOptionsIDX();

        final List<OptionIDX> optionIDXList = new ArrayList<>();
        optionIDXList.addAll(ceOptionsIDX);
        optionIDXList.addAll(peOptionsIDX);

        //find all the expiry dates
        List<String> expires = optionIDXList.stream().map(OptionIDX::getExpiry).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(optionIDXList)) {
            Map<String, List<OptionIDX>> maps = optionIDXList.stream().filter(id -> Double.parseDouble(id.getOpenInterest()) > 0.0)
                    //.sorted(Comparator.comparing(OptionIDX::getOpenInterest).reversed())
                    .collect(Collectors.groupingBy(OptionIDX::getExpiry));
//            maps.entrySet().stream().filter(k -> k.getKey().equalsIgnoreCase("07-Apr-2022")).forEach(s -> {
//                System.out.println(s);
//                System.out.println("");
//            });

            //convert map to the pcr ratio sorted by date
            List<OptionPcrRation> optionPcrRationList = maps.entrySet().stream().filter(k -> k.getKey().equalsIgnoreCase("13-Apr-2022")).map(this::calculatePcrRatio).collect(Collectors.toList());
        }
        return null;
    }

    private <R> OptionPcrRation calculatePcrRatio(Map.Entry<String, List<OptionIDX>> entry) {
        final OptionPcrRation optionPcrRation = new OptionPcrRation();
        optionPcrRation.setDate(getDate(entry.getKey()));
        List<OptionIDX> optionIDXList = entry.getValue();
        OptionIDX ce = optionIDXList.stream().filter(s -> s.getOptionType().equalsIgnoreCase("CE"))
                .sorted(Comparator.comparing(OptionIDX::getOpenInterest).reversed())
                .collect(Collectors.toList()).get(0);
        OptionIDX pe = optionIDXList.stream().filter(s -> s.getOptionType().equalsIgnoreCase("PE"))
                .sorted(Comparator.comparing(OptionIDX::getOpenInterest).reversed())
                .collect(Collectors.toList()).get(0);
        List<OptionIDX> highestPeCe = new ArrayList<>();
        highestPeCe.add(ce);
        highestPeCe.add(pe);
        optionPcrRation.setOptionIDXList(highestPeCe);
        return optionPcrRation;
    }

    private Date getDate(String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            return formatter.parse(date);
        } catch (Exception e) {
            //TODO:
        }
        return null;
    }

    private List<OptionIDX> getCEOptionsIDX() throws IOException {
        return new CsvToBeanBuilder(new FileReader(
                "C:\\my work\\stock-apps\\option-backtesting" +
                        "\\src\\main\\resources\\data\\OPTIDX_NIFTY_CE_09-04-2021_TO_08-04-2022.csv"))
                .withType(OptionIDX.class)
                .withSkipLines(1)
                .build()
                .parse();

    }

    private List<OptionIDX> getPEOptionsIDX() throws IOException {
        return new CsvToBeanBuilder(new FileReader(
                "C:\\my work\\stock-apps\\option-backtesting" +
                        "\\src\\main\\resources\\data\\OPTIDX_NIFTY_PE_09-04-2021_TO_08-04-2022.csv"))
                .withType(OptionIDX.class)
                .withSkipLines(1)
                .build()
                .parse();

    }
}
