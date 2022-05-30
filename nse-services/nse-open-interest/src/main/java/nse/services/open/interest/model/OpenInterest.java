package nse.services.open.interest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "OPEN_INTEREST")
public class OpenInterest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String symbol;
    private LocalDateTime currentDateTime;
    //private Date expiryDate;
    private double maxCallOI;
    private double maxCallChangeOI;
    private double maxCallVolume;
    private double callAskPrice;
    private double callLtp;
    private double callStrikePrice;
    private double firstResistance;
    private double secondResistance;
    private double maxPutOI;
    private double maxPutChangeOI;
    private double maxPutVolume;
    private double putLtp;
    private double putStrikePrice;
    private double firstSupport;
    private double secondSupport;
    private double putAskPrice;
    private double pcrOI;
    private double pcrVolume;
    //private String note;
}
