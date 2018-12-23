package com.doctortech.fhq.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符集识别工具类
 */
public class CharacterUtil {
    public static final int CHAR_USELESS=0;
    public static final int CHAR_ARABIC=0X00000001;
    public static final int CHAR_ENGLISH=0X00000002;
    public static final int CHAR_CHINESE=0X00000004;
    public static final int CHAR_OTHER_CJK=0X00000008;

  
    /***
     * 进行字符规格化(全角转半角,大写转小写处理）
     * @param input
     * @param lowercase
     * @return
     */
    public static char regularize(char input,boolean lowercase){
        if(input == 12288){
            input=(char)32;
        }else if(input > 65280 && input < 65375){
            input = (char)(input - 65248);
        }else if(input >='A'&&input <='Z'&&lowercase){
            input +=32;
        }
        return input;
    }
    
    
    public static String cleanStr(String str) {
		if(str==null || str.equals(""))return "" ;
		char[] c=str.toCharArray();
		List<Character> list=new ArrayList<Character>();
		for(int i=0;i<c.length;i++) {
			if(check(c[i])) {
				list.add(regularize(c[i],false));
			}
		}
		char[] tt=new char[list.size()]; 
		int j=0 ;
		for(char ch:list) {
			tt[j]=ch ;
			j++ ;
		}
		return  new String(tt);
	}
	
	
	public static boolean check(char input) {
		 if(input >='0' && input <='9'){
             return true;
         }else  if((input >= 'a'&& input <='z')||(input >='A'&&input <='Z')){
             return true;
         }else if(input=='-'||input=='_'||input=='—') {
        	 return true ;
         }else{
        	 Character.UnicodeBlock ub=Character.UnicodeBlock.of(input);
        	 if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
        		|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
        		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
        		|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
        		|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
        		|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
        		 return true;
        	 }
         }			
		return false ;
	}
	
	
	/**
     * 过滤掉超过3个字节的UTF8字符
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }

            b += 256; // 去掉符号位

            if (((b >> 5) ^ 0x6) == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
            } else if (((b >> 4) ^ 0xE) == 0) {
                buffer.put(bytes, i, 3);
                i += 3;
            } else if (((b >> 3) ^ 0x1E) == 0) {
                i += 4;
            } else if (((b >> 2) ^ 0x3E) == 0) {
                i += 5;
            } else if (((b >> 1) ^ 0x7E) == 0) {
                i += 6;
            } else {
                buffer.put(bytes[i++]);
            }
        }
        buffer.flip();
        return new String(buffer.array(), "utf-8");
    }
	
    
//    public static String filterOffUtf8Mb4_2(String text) throws UnsupportedEncodingException {
//    	byte[] bytes = text.getBytes("utf-8");
//    	ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
//    	int i = 0;
//    	while (i < bytes.length) {
//    	short b = bytes[i];
//    	if (b > 0) {
//    	buffer.put(bytes[i++]);
//    	continue;
//    	}
//
//    	b += 256; //去掉符号位
//
//    	if (((b >> 5) ^ 0x06) == 0) {
//    	buffer.put(bytes, i, 2);
//    	i += 2;
//    	System.out.println("2");
//    	} else if (((b >> 4) ^ 0x0E) == 0) {
//    	System.out.println("3");
//    	buffer.put(bytes, i, 3);
//    	i += 3;
//    	} else if (((b >> 3) ^ 0x1E) == 0) {
//    	i += 4;
//    	System.out.println("4");
//    	} else if (((b >> 2) ^ 0xBE) == 0) {
//    	i += 5;
//    	System.out.println("5");
//    	} else {
//    	i += 6;
//    	System.out.println("6");
//    	}
//    	}
//    	buffer.flip();
//    	return new String(buffer.array(), "utf-8");
//    	}

    
	public static void main(String[] args) {
//		StringBuffer sb=new StringBuffer("温扬茂，博士，副教授，硕士生导师，珞珈青年学者，武汉大学测绘学院\r\n" + 
//				"研究方向：高精度InSAR数据处理、大地测量反演与地球动力学\r\n" + 
//				"湖北省武汉市珞喻路129号，430079\r\n" + 
//				"\r\n" + 
//				"教育经历\r\n" + 
//				"1999年9月&#82112003年6月，武汉大学测绘学院本科学习\r\n" + 
//				"2003年9月&#82112009年6月，武汉大学测绘学院硕博连读\r\n" + 
//				"2008年10月&#82112009年3月，英国格拉斯哥大学访问学生\r\n" + 
//				"\r\n" + 
//				"工作经历\r\n" + 
//				"2012年12月至今，武汉大学测绘学院，副教授\r\n" + 
//				"2013年6月&#82112014年6月，英国格拉斯哥大学/纽卡斯尔大学，访问学者\r\n" + 
//				"2009年7月&#82112012年11月，武汉大学测绘学院，讲师\r\n" + 
//				"2009年9月&#821110月，德国GFZ地学研究中心进修\r\n" + 
//				"\r\n" + 
//				"代表性论著\r\n" + 
//				"2018年\r\n" + 
//				"YiL.,C.Xu,Y.Wen,X.Zhang,G.Jiang(2018),Ruptureprocessofthe2016Mw7.8EcuadorearthquakefromjointinversionofInSARdataandeseismicPwaveforms,Tectonophysics,722:163-174,doi:10.1016/j.tecto.2017.10.028\r\n" + 
//				"XuG.,C.Xu,Y.Wen(2018),Sentinel-1observationofthe2017Sangsefidearthquake,northeasternIran:Ruptureofablindreserve-slipfaultneartheEasternKopehDagh, Tectonophysics, 731–732:131-138,doi:10.1016/j.tecto.2018.03.009\r\n" + 
//				"Xie X.,C.Xu,Y.Wen andW.Li(2018), MonitoringgroundwaterstoragechangesintheLoessPlateauusingGRACEsalitegravitydata,hydrologicalmodelsandcoalminingdata,RemoteSensing, 2018, 10(4),605,doi: 10.3390/rs10040605\r\n" + 
//				"谢曦霖,许才军,温扬茂*,周力璇(2018),一种基于多面函数的改进最小二乘配置方法,武汉大学学报(信息科学版),43(4):592-598,doi:10.13203/j.whugis20150664\r\n" + 
//				"温扬茂, 张国波,许才军(2018),川滇地区GPS速度场聚类分析,大地测量与地球动力学,38(5):447-453,doi:10.14075/j.jgg.2018.05.002\r\n" + 
//				"蒋生淼,易磊,张旭,温扬茂(2018),2016年日本熊本地震破裂时空过程联合反演,地震学报,40(1):13-23,doi:10.11939/jass.20170097\r\n" + 
//				"2017年\r\n" + 
//				"[70]XuG.,C.Xu,Y.Wen, G.Jiang(2017), SourceParametersofthe2016–2017CentralItalyEarthquakeSequencefromtheSentinel-1,ALOS-2andGPSData.RemoteSensing, 9(11):1182,doi:10.3390/rs9111182\r\n" + 
//				"[69]Wang,S.,C.Xu,Y.Wen,Z.Yin,G.JiangandL.Fang(2017),Slipmodelforthe25November2016Mw6.6Aketaoearthquake,westernChina,revealedbySentinel-1andALOS-2observations,RemoteSensing,9(4):325,doi:10.3390/rs9040325\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"[68]Yi,L.,C.Xu, X.Zhang,Y.Wen,G.Jiang,M.Li,Y.Wang(2017),JointinversionofGPS,InSARandeseismicdatasetsfortheruptureprocessofthe2015Gorkha,Nepal,earthquakeusingageneralizedABICmethod,JournalofAsianEarthSciences,148:121-130,doi:10.1016/j.jseaes.2017.08.029\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"[67]He,P.,E.A.Hetland,Q.Wang,K.Ding,Y.Wen,andR.Zou(2017),Coseismicslipinthe2016Mw7.8EcuadorearthquakeimagedfromSentinel-1Aradarinterferometry,SeismologicalResearchLetters,88(2A):277-286,doi:10.1785/0220160151\r\n" + 
//				"[66]FanQ.,C.Xu.L.Yi,Y.Liu,Y.Wen,Z.Yin(2017),ImplicationofadaptivesmoothnessconstraintandHelmertvariancecomponentestimationinseismicslipinversion, JournalofGeodesy, doi:10.1007/s00190-017-1015-0\r\n" + 
//				"[65]宋闯,许才军,温扬茂,易磊,徐文 (2017),利用高频GPS资料研究2016年新西兰凯库拉地震的地表形变及预警震级, 地球物理学报,60(9):3396-3405\r\n" + 
//				"[64]王乐洋,李海燕,温扬茂,许才军(2017),地震同震滑动分布反演的总体最小二乘方法,测绘学报,46(3):307-315\r\n" + 
//				"[63]王乐洋,陈汉清,温扬茂(2017),地壳形变分析的总体最小二乘配置方法,大地测量与地球动力学,37(2):163-168\r\n" + 
//				"[62]肖卓辉,许才军,江国焰,温扬茂(2017),汶川地震前十年间龙门山区域顾及断层闭锁的地壳应变场,地球物理学报,60(3):953-961\r\n" + 
//				"[61]刘洋,许才军,温扬茂(2017),测绘类专业“地球科学概论”理论教学的若干思考,测绘通报,42(6):146-148+152\r\n" + 
//				"[60]周旭,许才军,温扬茂 (2017),利用时序InSAR技术分析北京及河北廊坊地面沉降,测绘科学,42(7):89-93\r\n" + 
//				"2016年\r\n" + 
//				"[59]Wen,Y.,C.Xu,Y.Liu,G.Jiang(2016),Deformationandsourceparametersofthe2015Mw6.5earthquakeinPishan,WesternChina,fromSentinel-1AandALOS-2data,RemoteSensing, 8(2),134,doi:10.3390/rs8020134\r\n" + 
//				"[58]Zhu,S.,C.Xu,Y.Wen,Y.Liu(2016),InterseismicDeformationoftheAltynTaghFaultDeterminedbyInterferometricSyntheticApertureRadar(InSAR)Measurements.RemoteSensing,8(3),233,doi:10.3390/rs8030233\r\n" + 
//				"[57]Xu,C.,B.Xu,Y.Wen,Y.Liu(2016),HeterogeneousFaultMechanismsofthe6October2008Mw6.3Dangxiong(Tibet)EarthquakeUsingInterferometricSyntheticApertureRadarObservations.RemoteSensing,8(3),228,doi:10.3390/rs8030228\r\n" + 
//				"[56]YinZ.,C.Xu,Y.Wen,G.Jiang,Q.FanandY.Liu(2016),Anewhybridinversionmethodforparametriccurvedfaultsanditsapplicationtothe2008Wenchuan(China)earthquake,GeophysicalJournalInternational,  205(2): 954&#8211970,doi:10.1093/gji/ggw060\r\n" + 
//				"[55]LiuY., C.Xu, Y.Wenand Z.Li(2016),Post-seismicdeformationfromthe2009Mw6.3DachaidanearthquakeintheNorthernQaidamBasindetectedbysmallbaselinesubsetInSARtechnique,Sensors,16(2),206,doi:10.3390/s16020206\r\n" + 
//				"[54]LiuY., C.Xu, Z.Li,Y.Wen,J.Chenand Z.Li(2016),Time-dependentafterslipofthe2009Mw6.3Dachaidanearthquake(China)andviscositybeneaththeQaidamBasininferredfrompostseismicdeformationobservations,RemoteSensing,8(8),649,doi:10.3390/rs8080649\r\n" + 
//				"[53]LuoH.,Y.Liu,T.Chen,C.XuandY.Wen(2016),Derivationofthree-dimensionalsurfacedeformationfromanintegrationofInSARandGNSSmeasurementsbasedonAkaike&#8217sBayesianInformationCriterion, GeophysicalJournalInternational,204(1): 292-310.doi:10.1093/gji/ggv453\r\n" + 
//				"[52]HeP.,Q.Wang,K.Ding,M.Wang,X.Qiao,J.Li,Y.Wen,C.Xu,S.Yang,andR.Zou(2016),Sourcemodelofthe2015Mw6.4PishanearthquakeconstrainedbyInSARandGPS:insightintoblindruptureinthewesternKunlunShan,GeophysicalResearchLetter,43:1511–1519, doi:10.1002/2015GL067140\r\n" + 
//				"[51]李永生,申文豪,温扬茂,张景发,李振洪,姜文亮,罗毅(2016),2015年尼泊尔Mw7.8地震震源机制InSAR反演及强地面运动模拟,地球物理学报,59(4):1359-1370\r\n" + 
//				"[50]何平,许才军,温扬茂,丁开华,王琪(2016),时序InSAR的误差模型建立及模拟研究,武汉大学学报（信息科学版）,41(6):752-758\r\n" + 
//				"[49]刘洋,许才军,温扬茂(2016),两次大柴旦Mw6.3地震间地表形变的InSAR观测及与同震破裂的联合分析,大地测量与地球动力学,36(2):110-114,doi:10.14075/j.jgg.2016.02.004\r\n" + 
//				"[48]刘洋,许才军,温扬茂(2016),单幅干涉图像提取震间形变场模拟研究,测绘科学,41(3):12-17\r\n" + 
//				"2015年\r\n" + 
//				"[47]LiuY.,C.Xu,Y.Wen,H.S.Fok(2015),Anewperspectiveonfaultgeometryandslipdistributionofthe2009DachaidanMw6.3earthquakefromInSARobservations,Sensors,15:16786-16803,doi:10.3390/s150716786\r\n" + 
//				"[46]HeP.,Y.Wen,C.Xu,Y.Liu,andH.S.Fok(2015),NewevidenceforactivetectonicsattheboundaryoftheKashiDepression,China,fromtimeseriesInSARobservations,Tectonophysics,653:140-148,doi:10.1016/j.tecto.2015.04.011\r\n" + 
//				"[45]Jiang,G.,Y.Wen*,Y.Liu,X.Xu,L.Fang,G.Chen,M.Gong,andC.Xu(2015),Jointanalysisofthe2014Kangding,southwestChina,earthquakesequencewithseismicityrelocationandInSARinversion,GeophysicalResearchLetter,42,3273–3281,doi:10.1002/2015GL063750\r\n" + 
//				"[44]Zhou,Y.,C.Zhou,F.Deng,D.E,H.Liu,andY.Wen(2015),ImprovingInSARelevationmodelsinAntarcticausinglaseraltimetry,accountingforicemotion,orbitalerrorsandatmosphericdelays,RemoteSensingofEnvironment,162:112-118\r\n" + 
//				"[43]温扬茂,许才军,刘洋,冯万鹏,李志才(2015),升降轨InSAR数据约束下的2007年阿里地震反演分析,测绘学报,44(6):649-654\r\n" + 
//				"[42]刘洋,许才军,温扬茂,何平(2015),2008年大柴旦Mw 6.3级地震的InSAR同震形变观测及断层参数反演,测绘学报,44(11),1202-1209\r\n" + 
//				"[41]许才军,何平,温扬茂,刘洋(2015),InSAR技术及应用研究进展,测绘地理信息,40(2),1-9\r\n" + 
//				"[40]何平,许才军,温扬茂,丁开华,王庆良(2015),利用PALSAR数据研究长白山火山活动性,武汉大学学报(信息科学版),40(2),214-221\r\n" + 
//				"2014年\r\n" + 
//				"[39]Wang,T.,F.He,A.Zhang,L.Gu,Y.Wen,W.Jiang,andH.Shao(2014),AQuantitativeStudyofGullyErosionBasedonObject-OrientedAnalysisTechniques:ACaseStudyinBeiyanzikouCatchmentofQixia,Shandong,China,TheScientificWorldJournal,http://dx.doi.org/10.1155/2014/417325\r\n" + 
//				"[38]Jiang,G.,C.Xu,Y.Wen,X.Xu,K.Ding,andJ.Wang(2014),Contemporarytectonicstressingratesofmajorstrike-slipfaultsintheTibetanPlateaufromGPSobservationsusingleast-squarescollocation,Tectonophysics,615–616,85–95\r\n" + 
//				"[37]温扬茂,许才军,李振洪,刘洋,冯万鹏,单新建(2014),InSAR约束下的2008年汶川地震同震和震后形变分析,地球物理学报,57(6),1814-1824\r\n" + 
//				"[36]李志才,许才军,张鹏,温扬茂(2014),顾及地壳粘弹性结构的地震断层震后形变反演分析,武汉大学学报(信息科学版),39(12),1477-1481\r\n" + 
//				"2013年\r\n" + 
//				"[35]Wen,Y.,C.Xu,Y.Liu,G.Jiang,andP.He(2013),Coseismicslipinthe2010Yushuearthquake(China),constrainedbywide-swathandstrip-mapInSAR,Nat.HazardsEarthSyst.Sci.,13(1),35-44,doi:10.5194/nhess-13-35-2013.\r\n" + 
//				"[34]He,P.,Y.Wen,C.Xu,andY.Liu(2013),Thelargeaftershockstriggeredbythe2011Mw9.0Tohoku-Okiearthquake,Japan,JournalofAsianEarthSciences,74,1-10\r\n" + 
//				"[33]Jiang,G.,C.Xu,Y.Wen,Y.Liu,Z.Yin,andJ.Wang(2013),Inversionforcoseismicslipdistributionofthe2010Mw6.9YushuEarthquakefromInSARdatausingangulardislocations,GeophysicalJournalInternational,194(2):1011-1022.\r\n" + 
//				"[32]王乐洋,许才军,温扬茂(2013),利用STLN和InSAR数据反演2008年青海大柴旦Mw6.3级地震断层参数,测绘学报,42(2),168-176.\r\n" + 
//				"[31]丁开华,许才军,温扬茂(2013),汶川地震震后形变的GPS反演,武汉大学学报(信息科学版),38(02),131-135.\r\n" + 
//				"[30]李志才,张鹏,温扬茂,廖瑛(2013),利用GPS和海底基准点观测形变反演日本大地震(Mw9.0)同震断层滑动分布,武汉大学学报(信息科学版),38(01),40-43.\r\n" + 
//				"[29]王涛,顾丽娟,詹华明,温扬茂,洪顺英,何福红(2013),基于D-InSAR技术的天津地区地面沉降监测,测绘科学,38(6),49-51\r\n" + 
//				"2012年\r\n" + 
//				"[28]Wen,Y.,Z.Li,C.Xu,I.Ryder,andR.Bürgmann(2012),Postseismicmotionafterthe2001MW7.8KokoxiliearthquakeinTibetobservedbyInSARtimeseries,J.Geophys.Res.,117(B8),B08405,doi:10.1029/2011JB009043.\r\n" + 
//				"[27]Liu,Y.,C.Xu,Y.Wen,P.He,andG.Jiang(2012),Faultrupturemodelofthe2008Dangxiong(Tibet,China)Mw6.3earthquakefromEnvisatandALOSdata,AdvancesinSpaceResearch,50(7),952-962,doi:10.1016/j.asr.2012.06.006.\r\n" + 
//				"[26]温扬茂,何平,许才军,刘洋(2012),联合Envisat和ALOS卫星影像确定L′Aquila地震震源机制,地球物理学报,55(01),53-65.\r\n" + 
//				"[25]温扬茂,许才军,刘洋,何平(2012),利用断层自动剖分技术的2008年青海大柴旦Mw6.3级地震InSAR反演研究,武汉大学学报(信息科学版),37(04),458-462+507.\r\n" + 
//				"[24]何平,温扬茂,许才军,李志才(2012),用多时相InSAR技术研究廊坊地区地下水体积变化,武汉大学学报(信息科学版),37(10),1181-1185.\r\n" + 
//				"[23]许才军,何平,温扬茂,杨永林(2012),利用CR-InSAR技术研究鲜水河断层地壳形变,武汉大学学报(信息科学版),37(03),302-305.\r\n" + 
//				"[22]许才军,何平,温扬茂,张磊(2012),日本2011Tohoku-OkiMw9.0级地震的同震形变及其滑动分布反演:GPS和InSAR约束,武汉大学学报(信息科学版),37(12),1387-1391+1384.\r\n" + 
//				"[21]许才军,江国焰,汪建军,温扬茂(2012),基于GNSS/InSAR/GIS的活动断层地震危险性评估系统,测绘学报,41(05),661-669.\r\n" + 
//				"2011年\r\n" + 
//				"[20]Xu,C.J.,L.Y.Wang,Y.M.Wen,andJ.J.Wang(2011),StrainRatesintheSichuan-YunnanRegionBasedupontheTotalLeastSquaresHeterogeneousStrainModelfromGPSData,TerrestrialAtmosphericandOceanicSciences,22(2),133-147,doi:10.3319/TAO.2010.07.26.02(TibXS).\r\n" + 
//				"[19]Liu,Y.,C.Xu,Z.Li,Y.Wen,andD.Forrest(2011),InterseismicsliprateoftheGarze–YushufaultbeltintheTibetanPlateaufromC-bandInSARobservationsbetween2003and2010,AdvancesinSpaceResearch,48(12),2005-2015,doi:10.1016/j.asr.2011.08.020.\r\n" + 
//				"[18]Shan,B.,X.Xiong,Y.Zheng,S.Wei,Y.Wen,B.Jin,andC.Ge(2011),Theco-seismicCoulombstresschangeandexpectedseismicityratecausedby14April2010Ms=7.1Yushu,China,earthquake,Tectonophysics,510(3-4),345-353,doi:10.1016/j.tecto.2011.08.003.\r\n" + 
//				"[17]许才军,何平,温扬茂(2011),利用PSInSAR研究意大利Etna火山的地表形变,武汉大学学报(信息科学版),36(09),1012-1016.\r\n" + 
//				"[16]江国焰,许才军,李明峰,温扬茂(2011),利用两种z变换算法的PS-DInSAR相位解缠与等价性证明,武汉大学学报(信息科学版),36(03),338-341.\r\n" + 
//				"[15]许才军,江国焰,王浩,温扬茂(2011),基于GIS的InSAR结果分析方法及在汶川Mw7.9级地震同震解释中的应用,武汉大学学报(信息科学版),36(04),379-383+376+507.\r\n" + 
//				"2010年\r\n" + 
//				"[14]Xu,C.,Y.Liu,Y.Wen,andR.Wang(2010),CoseismicSlipDistributionofthe2008Mw7.9WenchuanEarthquakefromJointInversionofGPSandInSARData,BulletinoftheSeismologicalSocietyofAmerica,100(5B),2736-2749,doi:10.1785/0120090253.\r\n" + 
//				"[13]许才军,林敦灵,温扬茂(2010),利用InSAR数据的汶川地震形变场提取及分析,武汉大学学报(信息科学版),35(10),1138-1142+1261-1262.\r\n" + 
//				"2009年\r\n" + 
//				"Wen,Y.M.,andC.J.Xu(2009),StaticStressChangefromthe8November,1997Ms7.9Manyi,TibetEarthquakeasInferredfromInSARObservation,inObservingOurChangingEarth,editedbyM.G.Sideris,pp.793-801.\r\n" + 
//				"[11]Wu,J.,C.Xu,Y.Wen,andF.Xiao(2009),ExperimentalPALSARinterferometryinShanghaiforgroundsubsidencemonitoring,paperpresentedatALOSPI2008Symposium,November3,2008&#8211November7,2008,EuropeanSpaceAgency,IslandofRhodes,Greece.\r\n" + 
//				"[10]温扬茂,许才军(2009),基于敏感度的迭代拟合法反演玛尼Ms7.9级地震滑动分布,武汉大学学报(信息科学版),34(06),732-735.\r\n" + 
//				"[9]温扬茂,许才军(2009),联合GPS与重力资料反演分析川滇地区现今地壳形变,武汉大学学报(信息科学版),34(05),568-572.\r\n" + 
//				"[8]许才军,刘洋,温扬茂(2009),利用GPS资料反演汶川Mw7.9级地震滑动分布,测绘学报,38(03),195-201+215.\r\n" + 
//				"[7]许才军,汪建军,温扬茂(2009),震后松弛过程的粘弹性模型在1997年Mw7.6玛尼地震中的应用研究,武汉大学学报(信息科学版),34(03),253-256+250.\r\n" + 
//				"[6]李志才,张鹏,金双根,蒋志浩,温扬茂(2009),基于GPS观测数据的汶川地震断层形变反演分析,测绘学报,38(02),108-113+119.\r\n" + 
//				"2008年\r\n" + 
//				"[5]许才军,温扬茂(2008),基于InSAR数据的西藏玛尼Ms7.9级地震的地壳不均匀性研究,武汉大学学报(信息科学版),33(08),846-849.\r\n" + 
//				"[4]李志才,许才军,张鹏,温扬茂(2008),基于地壳分层的地震断层同震变形反演分析,武汉大学学报(信息科学版),33(03),229-232.\r\n" + 
//				"2007年\r\n" + 
//				"[3]Xu,C.,andY.Wen(2007),IdentificationandanalysisofcrustalmotionanddeformationmodelsintheSichuan-Yunnanregion,JOURNALOFAPPLIEDGEODESY,1(4),213-222.\r\n" + 
//				"2006年\r\n" + 
//				"[2]李志才,许才军,赵少荣,温扬茂(2006),基于地壳分层的震后变形分析,武汉大学学报(信息科学版),31(03),203-208.\r\n" + 
//				"2003年\r\n" + 
//				"[1]许才军,温扬茂(2003),活动地块运动和应变模型辨识,大地测量与地球动力学,23(03),50-55.\r\n" + 
//				"\r\n" + 
//				"科技奖励\r\n" + 
//				"2016年高等学校科学研究优秀成果奖（自然科学奖）一等奖（序2）\r\n" + 
//				"2016年测绘科技进步奖一等奖（序2）\r\n" + 
//				"\r\n" + 
//				"在研科研项目\r\n" + 
//				"1.国家自然科学基金项目“巴颜喀拉块体的现今三维地壳形变与构造活动研究&#8221,2018-2021,主持\r\n" + 
//				"2.国家自然科学基金项目“时序InSAR震后形变的龙门山岩石圈流变特征研究”，2013-2015，主持\r\n" + 
//				"3.国家自然科学基金重点项目“动态大地测量地球物理数据联合反演模式及应用研究”，2015-2019,参与\r\n" + 
//				"4.国家自然科学基金中英合作研究项目“面向社区的减轻地震次生灾害风险研究”，2016-2018，参与\r\n" + 
//				"5.国家重点基础研究发展计划和重大科学研究计划项目（973项目）“对地观测、形变与地震波数据联合反演活动断层参数”，2013-2017，参与");
		
		StringBuffer sb=new StringBuffer("姓名\r\n" + 
				"印寅\r\n" + 
				"性别\r\n" + 
				"男\r\n" + 
				"职称\r\n" + 
				"讲师\r\n" + 
				"职务\r\n" + 
				"学历/学位\r\n" + 
				"博士\r\n" + 
				"导师类别\r\n" + 
				"其它\r\n" + 
				"所在学院\r\n" + 
				"航空宇航学院\r\n" + 
				"专业\r\n" + 
				"飞行器设计\r\n" + 
				"研究方向\r\n" + 
				"飞行器起落装置设计\r\n" + 
				"社会兼职\r\n" + 
				"荣誉称号\r\n" + 
				"科研情况\r\n" + 
				"主要科研成果\r\n" + 
				"[1]Yin Yin， Nie Hong， Zhang Ming， Ni Huajin. Reliability Analysis of Landing Gear Retraction System Influenced by Multi-factors. Journal of Aircraft， 2016，53(3): 713-724 （SCI,EI）[2]Yin Yin， Nie Hong， Wei Xiaohui， Chen Heng， Zhang Ming. Fault Analysis and Solution of an Airplane NLG’s Emergency Lowering. Journal of Aircraft, 2016，53(4): 1022-1032（SCI,EI）[3]Yin Yin， Nie Hong， Feng Fei， Wei Xiaohui， Ni Huajin. Non-linear Assembly Tolerance Design of Spatial Mechansims Based on Reliability Mehod. Journal of Mechanical Design，, 2017，139(3)（SCI,EI）\r\n" + 
				"学术经历\r\n" + 
				"2005.09-2009.06 南京航空航天大学飞行器设计与工程专业，本科,2009.09-2017.04 南京航空航天大学飞行器设计专业 硕博,\r\n" + 
				"其它");
		String ss="";
		try {
			ss = CharacterUtil.filterOffUtf8Mb4(sb.toString());
			System.out.println(ss);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
}
