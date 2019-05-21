package com.data.service.dataservice.util;

import java.util.HashMap;
import java.util.Map;

public final class SymbolMapper {
	
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
		symbolMapper.put("ALLCARGO","3456257");
		symbolMapper.put("FINPIPE","266497");
		symbolMapper.put("NESCO","3944705");
		symbolMapper.put("LAURUSLABS","4923905");
		symbolMapper.put("TATACOFFEE","185345");
		symbolMapper.put("TTKPRESTIG","907777");
		symbolMapper.put("JKPAPER","3036161");
		symbolMapper.put("SADBHAV","3388417");
		symbolMapper.put("DBCORP","4577537");
		symbolMapper.put("AKZOINDIA","375553");
		symbolMapper.put("JINDALSAW","774145");
		symbolMapper.put("JUSTDIAL","7670273");
		symbolMapper.put("CENTURYPLY","3406081");
		symbolMapper.put("LINDEINDIA","416513");
		symbolMapper.put("IRCON","1276417");
		symbolMapper.put("ZYDUSWELL","1276417");
		symbolMapper.put("MAHSCOOTER","533761");
		symbolMapper.put("GESHIP","3526657");
		symbolMapper.put("HIMATSEIDE","348161");
		symbolMapper.put("GAYAPROJ","6405889");
		symbolMapper.put("KOLTEPATIL","3871745");
		symbolMapper.put("GSFC","319233");
		symbolMapper.put("GRANULES","3039233");
		symbolMapper.put("ASTRAZEN","1436161");
		symbolMapper.put("MAHSEAMLES","534529");
		symbolMapper.put("ECLERX","3885825");
		symbolMapper.put("INDIACEM","387841");
		symbolMapper.put("SUVEN","2875649");
		symbolMapper.put("SOUTHBANK","1522689");
		symbolMapper.put("TATAELXSI","873217");
		symbolMapper.put("REPCOHOME","7577089");
		symbolMapper.put("TRIDENT","2479361");
		symbolMapper.put("ITI","428801");
		symbolMapper.put("HEIDELBERG","592897");
		symbolMapper.put("VINATIORGA","4445185");
		symbolMapper.put("CCL","2931713");
		symbolMapper.put("ITDCEM","1439233");
		symbolMapper.put("KSCL","3832833");
		symbolMapper.put("BASF","94209");
		symbolMapper.put("SCI","780289");
		symbolMapper.put("VRLLOG","2226177");
		symbolMapper.put("ALBK","2760193");
		symbolMapper.put("KTKBANK","2061825");
		symbolMapper.put("VENKEYS","961793");
		symbolMapper.put("LAXMIMACH","506625");
		symbolMapper.put("UCOBANK","2873089");
		symbolMapper.put("CARBORUNIV","152321");
		symbolMapper.put("FDC","1253889");
		symbolMapper.put("CESC","160769");
		symbolMapper.put("MONSANTO","1857793");
		symbolMapper.put("HFCL","5619457");
		symbolMapper.put("AEGISCHEM","10241");
		symbolMapper.put("TIINDIA","79873");
		symbolMapper.put("KEI","3407361");
		symbolMapper.put("TEAMLEASE","3255297");
		symbolMapper.put("RAYMOND","731905");
		symbolMapper.put("JPASSOCIAT","2933761");
		symbolMapper.put("SFL","4911105");
		symbolMapper.put("GEPIL","2012673");
		symbolMapper.put("MMTC","4596993");
		symbolMapper.put("CDSL","5420545");
		symbolMapper.put("PERSISTENT","4701441");
		symbolMapper.put("SUNCLAYLTD","7426049");
		symbolMapper.put("RITES","962817");
		symbolMapper.put("MOIL","5332481");
		symbolMapper.put("IBULISL","6399745");
		symbolMapper.put("FSL","3661825");
		symbolMapper.put("DIXON","5552641");
		symbolMapper.put("SUDARSCHEM","851713");
		symbolMapper.put("SWANENERGY","6936321");
		symbolMapper.put("VAKRANGEE","3415553");
		symbolMapper.put("GMDCLTD","1332225");
		symbolMapper.put("GODFRYPHLP","302337");
		symbolMapper.put("ASTERDM","386049");
		symbolMapper.put("BEML","101121");
		symbolMapper.put("SHOPERSTOP","3024129");
		symbolMapper.put("ADANIGREEN","912129");
		symbolMapper.put("CHENNPETRO","524545");
		symbolMapper.put("JAICORPLTD","1316609");
		symbolMapper.put("GHCL","288513");
		symbolMapper.put("JISLJALEQS","2661633");
		symbolMapper.put("NBVENTURES","1027585");
		symbolMapper.put("KARURVYSYA","470529");
		symbolMapper.put("TIMKEN","3634689");
		symbolMapper.put("PHILIPCARB","678145");
		symbolMapper.put("ADVENZYMES","4617985");
		symbolMapper.put("SHK","2870273");
		symbolMapper.put("VSTIND","953345");
		symbolMapper.put("UJJIVAN","4369665");
		symbolMapper.put("TNPL","1018881");
		symbolMapper.put("WOCKPHARMA","1921537");
		symbolMapper.put("INOXWIND","2010113");
		symbolMapper.put("NFL","3564801");
		symbolMapper.put("IFBIND","380161");
		symbolMapper.put("PTC","2906881");
		symbolMapper.put("EQUITAS","4314113");
		symbolMapper.put("CREDITACC","1131777");
		symbolMapper.put("CHAMBLFERT","163073");
		symbolMapper.put("HINDCOPPER","4592385");
		symbolMapper.put("DCBBANK","3513601");
		symbolMapper.put("VTL","530689");
		symbolMapper.put("SUPRAJIT","2992385");
		symbolMapper.put("KNRCON","3912449");
		symbolMapper.put("LAKSHVILAS","504321");
		symbolMapper.put("JAGRAN","3382017");
		symbolMapper.put("LUXIND","2893057");
		symbolMapper.put("LEMONTREE","667137");
		symbolMapper.put("NIITTECH","2955009");
		symbolMapper.put("JKTYRE","3695361");
		symbolMapper.put("UFLEX","269569");
		symbolMapper.put("NILKAMAL","619777");
		symbolMapper.put("PVR","3365633");
		symbolMapper.put("BIRLACORPN","122881");
		symbolMapper.put("WELCORP","3026177");
		symbolMapper.put("SONATSOFTW","1688577");
		symbolMapper.put("RENUKA","3078657");
		symbolMapper.put("BOMDYEING","131329");
		symbolMapper.put("NETWORK18","3612417");
		symbolMapper.put("JBCHEPHARM","441857");
		symbolMapper.put("IRB","3920129");
		symbolMapper.put("NH","3031041");
		symbolMapper.put("SIS","138412292");
		symbolMapper.put("MAHABANK","2912513");
		symbolMapper.put("VIPIND","947969");
		symbolMapper.put("VMART","7496705");
		symbolMapper.put("MHRIL","4437249");
		symbolMapper.put("PCJEWELLER","7455745");
		symbolMapper.put("BAJAJCON","4999937");
		symbolMapper.put("REDINGTON","3649281");
		symbolMapper.put("JKCEMENT","3397121");
		symbolMapper.put("GRINDWELL","3471361");
		symbolMapper.put("PRSMJOHNSN","701185");
		symbolMapper.put("BAJAJELEC","3848705");
		symbolMapper.put("AVANTIFEED","2031617");
		symbolMapper.put("SHILPAMED","4544513");
		symbolMapper.put("MAGMA","2919169");
		symbolMapper.put("JSLHISAR","3149825");
		symbolMapper.put("DEEPAKNTR","5105409");
		symbolMapper.put("ORIENTCEM","7702785");
		symbolMapper.put("SUNTECK","4516097");
		symbolMapper.put("THYROCARE","4360193");
		symbolMapper.put("SUZLON","3076609");
		symbolMapper.put("WABAG","5168129");
		symbolMapper.put("MASFIN","50945");
		symbolMapper.put("INDOSTAR","790529");
		symbolMapper.put("TAKE","3818753");
		symbolMapper.put("BDL","548865");
		symbolMapper.put("GREAVESCOT","316161");
		symbolMapper.put("JSL","2876417");
		symbolMapper.put("IBREALEST","3699201");
		symbolMapper.put("GUJALKALI","324353");
		symbolMapper.put("GET&D","4296449");
		symbolMapper.put("RADICO","2813441");
		symbolMapper.put("DEEPAKFERT","211713");
		symbolMapper.put("J&KBANK","1442049");
		symbolMapper.put("KALPATPOWR","464385");
		symbolMapper.put("SHARDACROP","1277953");
		symbolMapper.put("CORPBANK","1319425");
		symbolMapper.put("JKLAKSHMI","3453697");
		symbolMapper.put("PRAJIND","692481");
		symbolMapper.put("SREINFRA","836353");
		symbolMapper.put("SHANKARA","5202177");
		symbolMapper.put("TV18BRDCST","3637249");
		symbolMapper.put("DCAL","5556225");
		symbolMapper.put("ORIENTELEC","760833");
		symbolMapper.put("RHFL","5563649");
		symbolMapper.put("TCNSBRANDS","1068033");
		symbolMapper.put("TVTODAY","2886401");
		symbolMapper.put("SOBHA","3539457");
		symbolMapper.put("COX&KINGS","4558337");
	}

	public static Map<String, String> getSymbolMapper() {
		init();
		return symbolMapper;
	}
}
