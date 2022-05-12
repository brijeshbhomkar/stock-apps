package com.option.backtesting.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionPcrRation implements Serializable {

    private Date date;
    private List<OptionIDX> optionIDXList;
}
