package com.data.service.dataservice.util;

import java.util.HashMap;
import java.util.Map;

public final class StockSymbolMapper {
	
	private static final Map<String, String> symbolMapper = new HashMap<>();
	
	private static void init() {
		symbolMapper.put("IOC", "415745");
		symbolMapper.put("SUNPHARMA", "857857");
		symbolMapper.put("HINDALCO", "348929");
		symbolMapper.put("COALINDIA", "5215745");
		symbolMapper.put("VEDL", "784129");
		symbolMapper.put("SBIN", "779521");
		symbolMapper.put("ICICIBANK", "1270529");
		symbolMapper.put("CIPLA", "177665");
		symbolMapper.put("HINDPETRO", "359937");
		symbolMapper.put("BPCL", "134657");
		symbolMapper.put("NTPC", "2977281");
		symbolMapper.put("BHEL", "112129");
		symbolMapper.put("ONGC", "633601");
		symbolMapper.put("TATAMOTORS", "884737");
		symbolMapper.put("ITC", "424961");
		symbolMapper.put("POWERGRID", "3834113");
		symbolMapper.put("TATASTEEL", "895745");
		symbolMapper.put("GAIL", "1207553");
		symbolMapper.put("YESBANK", "3050241");
		symbolMapper.put("WIPRO", "969473");
		symbolMapper.put("JSWSTEEL", "3001089");
		symbolMapper.put("INFRATEL", "7458561");
		symbolMapper.put("BHARTIARTL", "2714625");
		symbolMapper.put("IBULHSGFIN", "7712001");
		symbolMapper.put("BAJAJ-AUTO", "4267265");
		symbolMapper.put("KOTAKBANK", "492033");
		symbolMapper.put("TITAN", "897537");
		symbolMapper.put("HEROMOTOCO", "345089");
		symbolMapper.put("BAJAJFINSV", "4268801");
		symbolMapper.put("HDFCBANK", "341249");
		symbolMapper.put("UPL", "2889473");
		symbolMapper.put("RELIANCE", "738561");
		symbolMapper.put("AXISBANK", "1510401");
		symbolMapper.put("MARUTI", "2815745");
		symbolMapper.put("LT", "2939649");
		symbolMapper.put("ASIANPAINT", "60417");
		symbolMapper.put("M&M", "519937");
		symbolMapper.put("ITC", "424961");
		symbolMapper.put("COALINDIA", "5215745");
		symbolMapper.put("GRASIM", "315393");
		symbolMapper.put("VEDL", "784129");
		symbolMapper.put("INDUSINDBK", "1346049");
		symbolMapper.put("DRREDDY", "225537");
		symbolMapper.put("CIPLA", "177665");
		symbolMapper.put("GAIL", "1207553");
		symbolMapper.put("BAJFINANCE", "81153");
		symbolMapper.put("WIPRO", "969473");
		symbolMapper.put("HDFC", "340481");
		symbolMapper.put("INFY", "408065");
		symbolMapper.put("EICHERMOT", "232961");
		symbolMapper.put("ULTRACEMCO", "2952193");
		symbolMapper.put("TATASTEEL", "895745");
		symbolMapper.put("IOC", "415745");
		symbolMapper.put("HCLTECH", "1850625");
		symbolMapper.put("ZEEL", "975873");
		symbolMapper.put("HINDUNILVR", "356865");
		symbolMapper.put("ADANIPORTS", "3861249");
		symbolMapper.put("TECHM", "3465729");
		symbolMapper.put("BRITANNIA", "140033");
		symbolMapper.put("TCS", "2953217");
		symbolMapper.put("MRF","582913");
		symbolMapper.put("SRTRANSFIN","1102337");
		symbolMapper.put("MCDOWELL-N","2674433");
		symbolMapper.put("ICICIGI","5573121");
		symbolMapper.put("HDFCAMC","1086465");
		symbolMapper.put("HDFCLIFE","119553");
		symbolMapper.put("NMDC","3924993");
		symbolMapper.put("PGHH","648961");
		symbolMapper.put("BANKBARODA","1195009");
		symbolMapper.put("CONCOR","1215745");
		symbolMapper.put("GICRE","70913");
		symbolMapper.put("DMART","5097729");
		symbolMapper.put("SAIL","758529");
		symbolMapper.put("BOSCHLTD","558337");
		symbolMapper.put("PAGEIND","3689729");
		symbolMapper.put("MOTHERSUMI","1076225");
		symbolMapper.put("SBILIFE","5582849");
		symbolMapper.put("HINDZINC","364545");
		symbolMapper.put("PETRONET","2905857");
		symbolMapper.put("BIOCON","2911489");
		symbolMapper.put("ASHOKLEY","54273");
		symbolMapper.put("LUPIN","2672641");
		symbolMapper.put("BAJAJHLDNG","78081");
		symbolMapper.put("AMBUJACEM","325121");
		symbolMapper.put("DIVISLAB","2800641");
		symbolMapper.put("OFSS","2748929");
		symbolMapper.put("DLF","3771393");
		symbolMapper.put("NHPC","4454401");
		symbolMapper.put("COLPAL","3876097");
		symbolMapper.put("PIDILITIND","681985");
		symbolMapper.put("SHREECEM","794369");
		symbolMapper.put("AUROPHARMA","70401");
		symbolMapper.put("INDIGO","2865921");
		symbolMapper.put("NIACL","102145");
		symbolMapper.put("L&TFH","6386689");
		symbolMapper.put("HINDPETRO","359937");
		symbolMapper.put("ABB","3329");
		symbolMapper.put("GODREJCP","2585345");
		symbolMapper.put("ACC","5633");
		symbolMapper.put("BANDHANBNK","579329");
		symbolMapper.put("BHEL","112129");
		symbolMapper.put("MARICO","1041153");
		symbolMapper.put("ICICIPRULI","4774913");
		symbolMapper.put("HAVELLS","2513665");
		symbolMapper.put("PEL","617473");
		symbolMapper.put("UBL","4278529");
		symbolMapper.put("CADILAHC","2029825");
		symbolMapper.put("DABUR","197633");
		symbolMapper.put("SIEMENS","806401");
		symbolMapper.put("IDEA","3677697");
		symbolMapper.put("DHFL","215553");
		symbolMapper.put("IGL","2883073");
		symbolMapper.put("TVSMOTOR","2170625");
		symbolMapper.put("PNB","2730497");
		symbolMapper.put("TORNTPHARM","900609");
		symbolMapper.put("BHARATFORG","108033");
		symbolMapper.put("TATAPOWER","877057");
		symbolMapper.put("LICHSGFIN","511233");
		symbolMapper.put("RAMCOCEM","523009");
		symbolMapper.put("UNIONBANK","2752769");
		symbolMapper.put("CANBK","2763265");
		symbolMapper.put("CUMMINSIND","486657");
		symbolMapper.put("IDFCFIRSTB","2863105");
		symbolMapper.put("GMRINFRA","3463169");
		symbolMapper.put("TATAGLOBAL","878593");
		symbolMapper.put("AJANTPHARM","2079745");
		symbolMapper.put("ADANIPOWER","4451329");
		symbolMapper.put("INDIANB","3663105");
		symbolMapper.put("APOLLOHOSP","40193");
		symbolMapper.put("MINDTREE","3675137");
		symbolMapper.put("FEDERALBNK","261889");
		symbolMapper.put("SRF","837889");
		symbolMapper.put("BEL","98049");
		symbolMapper.put("JINDALSTEL","1723649");
		symbolMapper.put("BANKINDIA","1214721");
		symbolMapper.put("IDBI","377857");
		symbolMapper.put("AMARAJABAT","25601");
		symbolMapper.put("RECLTD","3930881");
		symbolMapper.put("OIL","4464129");
		symbolMapper.put("M&MFIN","3400961");
		symbolMapper.put("NBCC","8042241");
		symbolMapper.put("PFC","3660545");
		symbolMapper.put("CASTROLIND","320001");
		symbolMapper.put("APOLLOTYRE","41729");
		symbolMapper.put("BALKRISIND","85761");
		symbolMapper.put("TATACHEM","871681");
		symbolMapper.put("CHOLAFIN","175361");
		symbolMapper.put("NATIONALUM","1629185");
		symbolMapper.put("JUBLFOOD","4632577");
		symbolMapper.put("MUTHOOTFIN","6054401");
		symbolMapper.put("BERGEPAINT","6054401");
		symbolMapper.put("HEXAWARE","2747905");
		symbolMapper.put("MRPL","584449");
		symbolMapper.put("RBLBANK","4708097");
		symbolMapper.put("EXIDEIND","173057");
		symbolMapper.put("GODREJIND","2796801");
		symbolMapper.put("RELINFRA","141569");
		symbolMapper.put("GLENMARK","1895937");
		symbolMapper.put("VOLTAS","951809");
		symbolMapper.put("SUNTV","3431425");
		symbolMapper.put("EDELWEISS","3870465");
		symbolMapper.put("IGL","2883073");
		symbolMapper.put("DHFL","215553");
		symbolMapper.put("TVSMOTOR","2170625");
		symbolMapper.put("RAJESHEXPO","1894657");
		symbolMapper.put("FRETAIL","4704769");
		symbolMapper.put("ALKEM","2995969");
		symbolMapper.put("PNB","2730497");
		symbolMapper.put("BHARATFORG","108033");
		symbolMapper.put("APLLTD","6483969");
		symbolMapper.put("TORNTPHARM","900609");
		symbolMapper.put("MAHINDCIE","3823873");
		symbolMapper.put("KRBL","2707713");
		symbolMapper.put("VBL","4843777");
		symbolMapper.put("NAUKRI","3520257");
		symbolMapper.put("STRTECH","2383105");
		symbolMapper.put("TATAPOWER","877057");
		symbolMapper.put("FINCABLES","265729");
		symbolMapper.put("PIIND","6191105");
		symbolMapper.put("HATSUN","996353");
		symbolMapper.put("RAMCOCEM","523009");
		symbolMapper.put("QUESS","4532225");
		symbolMapper.put("LICHSGFIN","511233");
		symbolMapper.put("GILLETTE","403457");
		symbolMapper.put("WABCOINDIA","4330241");
		symbolMapper.put("SJVN","4834049");
		symbolMapper.put("GMRINFRA","3463169");
		symbolMapper.put("UNIONBANK","2752769");
		symbolMapper.put("CANBK","2763265");
		symbolMapper.put("AIAENG","3350017");
		symbolMapper.put("JSWENERGY","4574465");
		symbolMapper.put("3MINDIA","121345");
		symbolMapper.put("ADANITRANS","2615553");
		symbolMapper.put("LTTS","4752385");
		symbolMapper.put("ERIS","5415425");
		symbolMapper.put("CUMMINSIND","486657");
		symbolMapper.put("IDFCFIRSTB","138095876");
		symbolMapper.put("CROMPTON","4376065");
		symbolMapper.put("INDIANB","3663105");
		symbolMapper.put("AJANTPHARM","2079745");
		symbolMapper.put("ASTRAL","3691009");
		symbolMapper.put("APOLLOHOSP","40193");
		symbolMapper.put("TRENT","502785");
		symbolMapper.put("AUBANK","5436929");
		symbolMapper.put("FEDERALBNK","261889");
		symbolMapper.put("ADANIPOWER","4451329");
		symbolMapper.put("SUNDARMFIN","854785");
		symbolMapper.put("HONAUT","874753");
		symbolMapper.put("MINDTREE","3675137");
		symbolMapper.put("TATAGLOBAL","878593");
		symbolMapper.put("PNBHOUSING","4840449");
		symbolMapper.put("BANKINDIA","1214721");
		symbolMapper.put("PFIZER","676609");
		symbolMapper.put("SRF","837889");
		symbolMapper.put("BEL","98049");
		symbolMapper.put("JINDALSTEL","1723649");
		symbolMapper.put("IDBI","377857");
		symbolMapper.put("ATUL","67329");
		symbolMapper.put("SOLARINDS","3412993");
		symbolMapper.put("CHOLAHLDNG","5565441");
		symbolMapper.put("BATAINDIA","94977");
		symbolMapper.put("RNAM","91393");
		symbolMapper.put("GLAXO","295169");
		symbolMapper.put("IPCALAB","418049");
		symbolMapper.put("VARROC","987393");
		symbolMapper.put("HAL","589569");
		symbolMapper.put("CRISIL","193793");
		symbolMapper.put("THERMAX","889601");
		symbolMapper.put("PRESTIGE","5197313");
		symbolMapper.put("OIL","4464129");
		symbolMapper.put("ENDURANCE","4818433");
		symbolMapper.put("IBVENTURES","3938305");
		symbolMapper.put("AMARAJABAT","25601");
		symbolMapper.put("KIOCL","4896257");
		symbolMapper.put("RECLTD","3930881");
		symbolMapper.put("GODREJAGRO","36865");
		symbolMapper.put("LTI","4561409");
		symbolMapper.put("MPHASIS","1152769");
		symbolMapper.put("NBCC","8042241");
		symbolMapper.put("GRUH","2957569");
		symbolMapper.put("PFC","3660545");
		symbolMapper.put("CASTROLIND","320001");
		symbolMapper.put("GSKCONS","821761");
		symbolMapper.put("MANAPPURAM","4879617");
		symbolMapper.put("M&MFIN","3400961");
		symbolMapper.put("MFSL","548353");
		symbolMapper.put("CENTRALBK","3812865");
		symbolMapper.put("HEG","342017");
		symbolMapper.put("TORNTPOWER","3529217");
		symbolMapper.put("NATCOPHARM","1003009");
		symbolMapper.put("JMFINANCIL","3491073");
		symbolMapper.put("HUDCO","5331201");
		symbolMapper.put("APOLLOTYRE","41729");
		symbolMapper.put("SUNDRMFAST","856321");
		symbolMapper.put("GODREJPROP","4576001");
		symbolMapper.put("BALKRISIND","85761");
		symbolMapper.put("PHOENIXLTD","3725313");
		symbolMapper.put("NLCINDIA","2197761");
		symbolMapper.put("BBTC","97281");
		symbolMapper.put("TATACHEM","871681");
		symbolMapper.put("GRAPHITE","151553");
		symbolMapper.put("NATIONALUM","1629185");
		symbolMapper.put("CHOLAFIN","175361");
		symbolMapper.put("BERGEPAINT","130426884");
		symbolMapper.put("JUBLFOOD","4632577");
		symbolMapper.put("SANOFI","369153");
		symbolMapper.put("COROMANDEL","189185");
		symbolMapper.put("SYMPHONY","6192641");
		symbolMapper.put("HEXAWARE","2747905");
		symbolMapper.put("MUTHOOTFIN","6054401");
		symbolMapper.put("MRPL","584449");
		symbolMapper.put("SYNGENE","2622209");
		symbolMapper.put("ENGINERSIN","1256193");
		symbolMapper.put("SHRIRAMCIT","3005185");
		symbolMapper.put("MGL","4488705");
		symbolMapper.put("RELAXO","6201601");
		symbolMapper.put("ISEC","637185");
		symbolMapper.put("RBLBANK","4708097");
		symbolMapper.put("BLUEDART","126721");
		symbolMapper.put("FCONSUMER","7689729");
		symbolMapper.put("EXIDEIND","173057");
		symbolMapper.put("GUJGASLTD","2713345");
		symbolMapper.put("WHIRLPOOL","4610817");
		symbolMapper.put("GODREJIND","2796801");
		symbolMapper.put("ABFRL","7707649");
		symbolMapper.put("CUB","136245764");
		symbolMapper.put("EIHOTEL","235265");
		symbolMapper.put("THOMASCOOK","891137");
		symbolMapper.put("GUJFLUORO","329985");
		symbolMapper.put("RELINFRA","141569");
		symbolMapper.put("KANSAINER","306177");
		symbolMapper.put("SKFINDIA","815617");
		symbolMapper.put("VGUARD","3932673");
		symbolMapper.put("MOTILALOFS","3826433");
		symbolMapper.put("EMAMILTD","3460353");
		symbolMapper.put("ABCAPITAL","5533185");
		symbolMapper.put("GLENMARK","1895937");
		symbolMapper.put("GSPL","3378433");
		symbolMapper.put("SUPREMEIND","860929");
		symbolMapper.put("OBEROIRLTY","5181953");
		symbolMapper.put("ESCORTS","245249");
		symbolMapper.put("SPARC","3785729");
		symbolMapper.put("INDHOTEL","387073");
		symbolMapper.put("VOLTAS","951809");
		symbolMapper.put("JUBILANT","931073");
		symbolMapper.put("DBL","4630017");
		symbolMapper.put("SUNTV","3431425");
		symbolMapper.put("RPOWER","3906305");
		symbolMapper.put("RELCAPITAL","737793");
		symbolMapper.put("DISHTV","3721473");
		symbolMapper.put("RCOM","3375873");
		symbolMapper.put("INOXLEISUR","3384577");
		symbolMapper.put("RAIN","3926273");
		symbolMapper.put("BLUESTARCO","2127617");
		symbolMapper.put("CGPOWER","194561");
		symbolMapper.put("JETAIRWAYS","2997505");
		symbolMapper.put("RCF","733697");
		symbolMapper.put("GPPL","5051137");
		symbolMapper.put("MINDACORP","6629633");
		symbolMapper.put("MERCK","240641");
		symbolMapper.put("IOB","2393089");
		symbolMapper.put("CEATLTD","3905025");
		symbolMapper.put("DCMSHRIRAM","207617");
		symbolMapper.put("GNFC","300545");
		symbolMapper.put("IDFC","3060993");
		symbolMapper.put("CAPLIPOINT","999937");
		symbolMapper.put("AAVAS","1378561");
		symbolMapper.put("INDOCO","2989313");
		symbolMapper.put("TRITURBINE","6549505");
		symbolMapper.put("BALRAMCHIN","87297");
		symbolMapper.put("CERA","3849985");
		symbolMapper.put("NCC","593665");
		symbolMapper.put("MINDAIND","3623425");
		symbolMapper.put("BRIGADE","136429828");
		symbolMapper.put("KAJARIACER","462849");
		symbolMapper.put("SYNDIBANK","1837825");
		symbolMapper.put("FINEORG","958465");
		symbolMapper.put("RUPA","6585345");
		symbolMapper.put("STAR","1887745");
		symbolMapper.put("ORIENTBANK","636673");
		symbolMapper.put("PNCINFRA","2402561");
		symbolMapper.put("GDL","3004161");
		symbolMapper.put("DELTACORP","3851265");
		symbolMapper.put("TATAINVEST","414977");
		symbolMapper.put("ZENSARTECH","275457");
		symbolMapper.put("MAXINDIA","4542721");
		symbolMapper.put("GULFOILLUB","1124097");
		symbolMapper.put("MAHLOG","98561");
		symbolMapper.put("IFCI","381697");
		symbolMapper.put("WELSPUNIND","2880769");
		symbolMapper.put("HATHWAY","4647425");
		symbolMapper.put("BALMLAWRIE","86529");
		symbolMapper.put("JAMNAAUTO","5319169");
		symbolMapper.put("ANDHRABANK","2524673");
		symbolMapper.put("COCHINSHIP","5506049");
		symbolMapper.put("ESSELPACK","251137");
		symbolMapper.put("CYIENT","1471489");
		symbolMapper.put("TIMETECHNO","3764993");
		symbolMapper.put("KEC","3394561");
		symbolMapper.put("RALLIS","720897");
		symbolMapper.put("GALAXYSURF","336641");
		symbolMapper.put("CARERATING","7452929");
		symbolMapper.put("IEX","56321");
		symbolMapper.put("BLISSGVS","4931841");
		symbolMapper.put("INFIBEAM","4159745");
		symbolMapper.put("HSCL","3669505");
		symbolMapper.put("APLAPOLLO","6599681");
		symbolMapper.put("HERITGFOOD","1177089");
		symbolMapper.put("FORTIS","3735553");
		symbolMapper.put("OMAXE","3802369");
		symbolMapper.put("LALPATHLAB","2983425");
		symbolMapper.put("STARCEMENT","5399297");
		symbolMapper.put("INTELLECT","1517057");
		symbolMapper.put("ELGIEQUIP","239873");
		symbolMapper.put("JYOTHYLAB","3877377");
		symbolMapper.put("KIRLOSENG","5359617");
		symbolMapper.put("NAVINFLUOR","3756033");
		symbolMapper.put("ICRA","3717889");
		symbolMapper.put("CANFINHOME","149249");
		symbolMapper.put("SPTL","5494273");
		symbolMapper.put("RKFORGE","2921217");
		symbolMapper.put("FLFL","7872513");
		symbolMapper.put("COFFEEDAY","2858241");
		symbolMapper.put("ITDC","4940545");
		symbolMapper.put("KPRMILL","3817473");
		symbolMapper.put("EIDPARRY","234497");
		symbolMapper.put("SCHAEFFLER","258817");
		symbolMapper.put("ASHOKA","5166593");
		symbolMapper.put("PARAGMILK","4385281");
//		symbolMapper.put("ALLCARGO","3456257");
//		symbolMapper.put("FINPIPE
//		symbolMapper.put("NESCO
//		symbolMapper.put("LAURUSLABS
//		symbolMapper.put("TATACOFFEE
//		symbolMapper.put("TTKPRESTIG
//		symbolMapper.put("JKPAPER
//		symbolMapper.put("SADBHAV
//		symbolMapper.put("DBCORP
//		symbolMapper.put("AKZOINDIA
//		symbolMapper.put("JINDALSAW
//		symbolMapper.put("JUSTDIAL
//		symbolMapper.put("CENTURYPLY
//		symbolMapper.put("LINDEINDIA
//		symbolMapper.put("IRCON
//		symbolMapper.put("ZYDUSWELL
//		symbolMapper.put("MAHSCOOTER
//		symbolMapper.put("GESHIP
//		symbolMapper.put("HIMATSEIDE
//		symbolMapper.put("GAYAPROJ
//		symbolMapper.put("KOLTEPATIL
//		symbolMapper.put("GSFC
//		symbolMapper.put("GRANULES
//		symbolMapper.put("ASTRAZEN
//		symbolMapper.put("MAHSEAMLES
//		symbolMapper.put("ECLERX
//		symbolMapper.put("INDIACEM
//		symbolMapper.put("SUVEN
//		symbolMapper.put("SOUTHBANK
//		symbolMapper.put("TATAELXSI
//		symbolMapper.put("REPCOHOME
//		symbolMapper.put("TRIDENT
//		symbolMapper.put("ITI
//		symbolMapper.put("HEIDELBERG
//		symbolMapper.put("VINATIORGA
//		symbolMapper.put("CCL
//		symbolMapper.put("ITDCEM
//		symbolMapper.put("KSCL
//		symbolMapper.put("BASF
//		symbolMapper.put("SCI
//		symbolMapper.put("VRLLOG
//		symbolMapper.put("ALBK
//		symbolMapper.put("KTKBANK
//		symbolMapper.put("VENKEYS
//		symbolMapper.put("LAXMIMACH
//		symbolMapper.put("UCOBANK
//		symbolMapper.put("CARBORUNIV
//		symbolMapper.put("FDC
//		symbolMapper.put("CESC
//		symbolMapper.put("MONSANTO
//		symbolMapper.put("HFCL
//		symbolMapper.put("AEGISCHEM
//		symbolMapper.put("TIINDIA
//		symbolMapper.put("KEI
//		symbolMapper.put("TEAMLEASE
//		symbolMapper.put("RAYMOND
//		symbolMapper.put("JPASSOCIAT
//		symbolMapper.put("SFL
//		symbolMapper.put("GEPIL
//		symbolMapper.put("MMTC
//		symbolMapper.put("CDSL
//		symbolMapper.put("PERSISTENT
//		symbolMapper.put("SUNCLAYLTD
//		symbolMapper.put("RITES
//		symbolMapper.put("MOIL
//		symbolMapper.put("IBULISL
//		symbolMapper.put("FSL
//		symbolMapper.put("DIXON
//		symbolMapper.put("SUDARSCHEM
//		symbolMapper.put("SWANENERGY
//		symbolMapper.put("VAKRANGEE
//		symbolMapper.put("GMDCLTD
//		symbolMapper.put("GODFRYPHLP
//		symbolMapper.put("ASTERDM
//		symbolMapper.put("BEML
//		symbolMapper.put("SHOPERSTOP
//		symbolMapper.put("ADANIGREEN
//		symbolMapper.put("CHENNPETRO
//		symbolMapper.put("JAICORPLTD
//		symbolMapper.put("GHCL
//		symbolMapper.put("JISLJALEQS
//		symbolMapper.put("NBVENTURES
//		symbolMapper.put("KARURVYSYA
//		symbolMapper.put("TIMKEN
//		symbolMapper.put("PHILIPCARB
//		symbolMapper.put("ADVENZYMES
//		symbolMapper.put("SHK
//		symbolMapper.put("VSTIND
//		symbolMapper.put("UJJIVAN
//		symbolMapper.put("TNPL
//		symbolMapper.put("WOCKPHARMA
//		symbolMapper.put("INOXWIND
//		symbolMapper.put("NFL
//		symbolMapper.put("IFBIND
//		symbolMapper.put("PTC
//		symbolMapper.put("EQUITAS
//		symbolMapper.put("CREDITACC
//		symbolMapper.put("CHAMBLFERT
//		symbolMapper.put("HINDCOPPER
//		symbolMapper.put("DCBBANK
//		symbolMapper.put("VTL
//		symbolMapper.put("SUPRAJIT
//		symbolMapper.put("KNRCON
//		symbolMapper.put("LAKSHVILAS
//		symbolMapper.put("JAGRAN
//		symbolMapper.put("LUXIND
//		symbolMapper.put("LEMONTREE
//		symbolMapper.put("NIITTECH
//		symbolMapper.put("JKTYRE
//		symbolMapper.put("UFLEX
//		symbolMapper.put("NILKAMAL
//		symbolMapper.put("PVR
//		symbolMapper.put("BIRLACORPN
//		symbolMapper.put("WELCORP
//		symbolMapper.put("SONATSOFTW
//		symbolMapper.put("RENUKA
//		symbolMapper.put("BOMDYEING
//		symbolMapper.put("NETWORK18
//		symbolMapper.put("JBCHEPHARM
//		symbolMapper.put("IRB
//		symbolMapper.put("NH
//		symbolMapper.put("SIS
//		symbolMapper.put("MAHABANK
//		symbolMapper.put("VIPIND
//		symbolMapper.put("VMART
//		symbolMapper.put("MHRIL
//		symbolMapper.put("PCJEWELLER
//		symbolMapper.put("BAJAJCON
//		symbolMapper.put("REDINGTON
//		symbolMapper.put("JKCEMENT
//		symbolMapper.put("GRINDWELL
//		symbolMapper.put("PRSMJOHNSN
//		symbolMapper.put("BAJAJELEC
//		symbolMapper.put("AVANTIFEED
//		symbolMapper.put("SHILPAMED
//		symbolMapper.put("MAGMA
//		symbolMapper.put("JSLHISAR
//		symbolMapper.put("DEEPAKNTR
//		symbolMapper.put("ORIENTCEM
//		symbolMapper.put("SUNTECK
//		symbolMapper.put("THYROCARE
//		symbolMapper.put("SUZLON
//		symbolMapper.put("WABAG
//		symbolMapper.put("MASFIN
//		symbolMapper.put("INDOSTAR
//		symbolMapper.put("TAKE
//		symbolMapper.put("BDL
//		symbolMapper.put("GREAVESCOT
//		symbolMapper.put("JSL
//		symbolMapper.put("IBREALEST
//		symbolMapper.put("GUJALKALI
//		symbolMapper.put("GET&D
//		symbolMapper.put("RADICO
//		symbolMapper.put("DEEPAKFERT
//		symbolMapper.put("J&KBANK
//		symbolMapper.put("KALPATPOWR
//		symbolMapper.put("SHARDACROP
//		symbolMapper.put("CORPBANK
//		symbolMapper.put("JKLAKSHMI
//		symbolMapper.put("PRAJIND
//		symbolMapper.put("SREINFRA
//		symbolMapper.put("SHANKARA
//		symbolMapper.put("TV18BRDCST
//		symbolMapper.put("DCAL
//		symbolMapper.put("ORIENTELEC
//		symbolMapper.put("RHFL
//		symbolMapper.put("TCNSBRANDS
//		symbolMapper.put("TVTODAY
//		symbolMapper.put("SOBHA
//		symbolMapper.put("COX&KINGS","");

	}

	public static Map<String, String> getSymbolMapper() {
		init();
		return symbolMapper;
	}
}
