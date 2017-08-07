package allData.dataVO;

public class StockMainVO {
	private String id;    //编号
	private String stockTime; //数据导入时间
	private String stockCode; //股票代码
	private String stockName; //股票名称
	private Double stockIncrease;  //涨幅  %
	private Double currentPrice;  //现价
	private Double goUpDrop;  //涨跌%
	private Double turnoverRate;  //换手%
	private Double stockAmplitude;  //振幅%
	private Double stockRising;  //涨速%
	private Double totalAmount;  //总金额
	private Double  totalCapAmount;//总市值
	private Double freeCapitalisation;  //流通市值
	private Double totalHands; //总手
	private String stockProfitable;  //利好
	private String stockBad;  //利空
	private Double mainForceVolume;  //主力净量
	private Double peRatio;  //市盈（动）
	private Double ttmPeRatio;  //TTM市盈率
	private Double peInterestRate;  //市净率
	private Double stockHand;  //现手
	private Double openingPrice;  //开盘
	private Double stockPrice;  //昨收
	private Double hightestPrice; //最高
	private Double minimumPrice;  //最低
	private Double purchasePrice;  //买价
	private Double sellingPrice;  //卖价
	private Double stockBuyNumber;  //买量
	private Double stockSellNumber; //卖量
	private Double completelyDish;  //委比%
	private Double volumeRatio;  //量比
	private Double macdNumber;  //金叉个数
	private Double stockNumber; //手/笔
	private Double outerDiscNumber;  //外盘
	private Double involNumber;  //内盘
	private String indutryOwned;  //所属行业
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getStockTime() {
		return stockTime;
	}


	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
	}


	public String getStockCode() {
		return stockCode;
	}


	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}


	public String getStockName() {
		return stockName;
	}


	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Double getStockIncrease() {
		return stockIncrease;
	}


	public void setStockIncrease(Double stockIncrease) {
		this.stockIncrease = (Double) (stockIncrease*0.01);
	}


	public Double getCurrentPrice() {
		return currentPrice;
	}


	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}


	public Double getGoUpDrop() {
		return goUpDrop;
	}


	public void setGoUpDrop(Double goUpDrop) {
		this.goUpDrop = (Double) (goUpDrop*0.01);
	}


	public Double getTurnoverRate() {
		return turnoverRate;
	}


	public void setTurnoverRate(Double turnoverRate) {
		this.turnoverRate = (Double) (turnoverRate*0.01);
	}


	public Double getStockAmplitude() {
		return stockAmplitude;
	}


	public void setStockAmplitude(Double stockAmplitude) {
		this.stockAmplitude = (Double) (stockAmplitude*0.01);
	}


	public Double getStockRising() {
		return stockRising;
	}


	public void setStockRising(Double stockRising) {
		this.stockRising = (Double) (stockRising*0.01);
	}


	public Double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Double getTotalCapAmount() {
		return totalCapAmount;
	}


	public void setTotalCapAmount(Double totalCapAmount) {
		this.totalCapAmount = totalCapAmount;
	}


	public Double getFreeCapitalisation() {
		return freeCapitalisation;
	}


	public void setFreeCapitalisation(Double freeCapitalisation) {
		this.freeCapitalisation = freeCapitalisation;
	}


	public Double getTotalHands() {
		return totalHands;
	}


	public void setTotalHands(Double totalHands) {
		this.totalHands = totalHands;
	}


	public String getStockProfitable() {
		return stockProfitable;
	}


	public void setStockProfitable(String stockProfitable) {
		this.stockProfitable = stockProfitable;
	}


	public String getStockBad() {
		return stockBad;
	}


	public void setStockBad(String stockBad) {
		this.stockBad = stockBad;
	}


	public Double getMainForceVolume() {
		return mainForceVolume;
	}


	public void setMainForceVolume(Double mainForceVolume) {
		this.mainForceVolume = mainForceVolume;
	}


	public Double getPeRatio() {
		return peRatio;
	}


	public void setPeRatio(Double peRatio) {
		this.peRatio = peRatio;
	}


	public Double getTtmPeRatio() {
		return ttmPeRatio;
	}


	public void setTtmPeRatio(Double ttmPeRatio) {
		this.ttmPeRatio = ttmPeRatio;
	}


	public Double getPeInterestRate() {
		return peInterestRate;
	}


	public void setPeInterestRate(Double peInterestRate) {
		this.peInterestRate = peInterestRate;
	}


	public Double getStockHand() {
		return stockHand;
	}


	public void setStockHand(Double stockHand) {
		this.stockHand = stockHand;
	}


	public Double getOpeningPrice() {
		return openingPrice;
	}


	public void setOpeningPrice(Double openingPrice) {
		this.openingPrice = openingPrice;
	}


	public Double getStockPrice() {
		return stockPrice;
	}


	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}


	public Double getHightestPrice() {
		return hightestPrice;
	}


	public void setHightestPrice(Double hightestPrice) {
		this.hightestPrice = hightestPrice;
	}


	public Double getMinimumPrice() {
		return minimumPrice;
	}


	public void setMinimumPrice(Double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}


	public Double getPurchasePrice() {
		return purchasePrice;
	}


	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


	public Double getSellingPrice() {
		return sellingPrice;
	}


	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	public Double getStockBuyNumber() {
		return stockBuyNumber;
	}


	public void setStockBuyNumber(Double stockBuyNumber) {
		this.stockBuyNumber = stockBuyNumber;
	}


	public Double getStockSellNumber() {
		return stockSellNumber;
	}


	public void setStockSellNumber(Double stockSellNumber) {
		this.stockSellNumber = stockSellNumber;
	}


	public Double getCompletelyDish() {
		return completelyDish;
	}


	public void setCompletelyDish(Double completelyDish) {
		this.completelyDish = (Double) (completelyDish*0.01);
	}


	public Double getVolumeRatio() {
		return volumeRatio;
	}


	public void setVolumeRatio(Double volumeRatio) {
		this.volumeRatio = volumeRatio;
	}


	public Double getMacdNumber() {
		return macdNumber;
	}


	public void setMacdNumber(Double macdNumber) {
		this.macdNumber = macdNumber;
	}


	public Double getStockNumber() {
		return stockNumber;
	}


	public void setStockNumber(Double stockNumber) {
		this.stockNumber = stockNumber;
	}


	public Double getOuterDiscNumber() {
		return outerDiscNumber;
	}


	public void setOuterDiscNumber(Double outerDiscNumber) {
		this.outerDiscNumber = outerDiscNumber;
	}


	public Double getInvolNumber() {
		return involNumber;
	}


	public void setInvolNumber(Double involNumber) {
		this.involNumber = involNumber;
	}


	public String getIndutryOwned() {
		return indutryOwned;
	}


	public void setIndutryOwned(String indutryOwned) {
		this.indutryOwned = indutryOwned;
	}


	@Override
	public String toString() {
		return "StockMainVO [id=" + id + ", stockTime=" + stockTime + ", stockCode=" + stockCode + ", stockName="
				+ stockName + ", stockIncrease=" + stockIncrease + ", currentPrice=" + currentPrice + ", goUpDrop="
				+ goUpDrop + ", turnoverRate=" + turnoverRate + ", stockAmplitude=" + stockAmplitude + ", stockRising="
				+ stockRising + ", totalAmount=" + totalAmount + ", freeCapitalisation=" + freeCapitalisation
				+ ", totalHands=" + totalHands + ", stockProfitable=" + stockProfitable + ", stockBad=" + stockBad
				+ ", mainForceVolume=" + mainForceVolume + ", peRatio=" + peRatio + ", ttmPeRatio=" + ttmPeRatio
				+ ", peInterestRate=" + peInterestRate + ", stockHand=" + stockHand + ", openingPrice=" + openingPrice
				+ ", stockPrice=" + stockPrice + ", hightestPrice=" + hightestPrice + ", minimumPrice=" + minimumPrice
				+ ", purchasePrice=" + purchasePrice + ", sellingPrice=" + sellingPrice + ", stockBuyNumber="
				+ stockBuyNumber + ", stockSellNumber=" + stockSellNumber + ", completelyDish=" + completelyDish
				+ ", volumeRatio=" + volumeRatio + ", macdNumber=" + macdNumber + ", stockNumber=" + stockNumber
				+ ", outerDiscNumber=" + outerDiscNumber + ", involNumber=" + involNumber + ", indutryOwned="
				+ indutryOwned + "]";
	}
	
	
	

}
