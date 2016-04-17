/**
 * 处理日期的显示
 */
function processDateForDisplay(dateStr){
	var dateStrForDisplay = null;
	if (dateStr == null || dateStr == "" || dateStr == "null" || dateStr == 0 || dateStr == '0'){
		dateStrForDisplay = "";
	} else {
		
		if(typeof(dateStr) == "string"){
			dateStr = new Date(parseInt(dateStr));
		} else if (typeof(dateStr == "number")){
			dateStr = new Date(dateStr);
		} 
		
		var dateStr_year = dateStr.getFullYear();
		var dateStr_month = dateStr.getMonth()+1;
		var dateStr_day = dateStr.getDate();
		var dateStr_hours = dateStr.getHours();	
		var dateStr_minutes = dateStr.getMinutes();
		if(dateStr_hours <10)
			dateStr_hours = "0"+dateStr_hours;
		if(dateStr_minutes <10)
			dateStr_minutes = "0"+dateStr_minutes;
		dateStrForDisplay = dateStr_year+"-"+dateStr_month+"-"+dateStr_day+" "+dateStr_hours+":"+dateStr_minutes;
	}
	return dateStrForDisplay;
}


/**
 * 处理在线时长的显示
 * @param dateMillis
 * @returns {String}
 */
function processTotalOnlineTime(dateMillis){
	var dateForDisplay = null;
	if (dateMillis == null || dateMillis == "" || dateMillis == "null" || dateMillis == 0 || dateMillis == '0'){
		dateStrForDisplay = "0";
	} else {
		var days = dateMillis / 1000 / 60 / 60 / 24;
		var daysForDisplay = Math.floor(days);
		var hours = dateMillis / 1000 / 60 / 60 - (24 * daysForDisplay);
		var hoursForDisplay = Math.floor(hours);
		var minutes = dateMillis / 1000 /60 - (24 * 60 * daysForDisplay) - (60 * hoursForDisplay);
		var minutesForDisplay = Math.floor(minutes);
	}
	if(daysForDisplay == 0){
		if(hoursForDisplay == 0){
			return minutesForDisplay+"分钟"
		} else {
			return hoursForDisplay+"小时"+minutesForDisplay+"分钟"
		}
	} else {
		return daysForDisplay+"天"+hoursForDisplay+"小时"+minutesForDisplay+"分钟";
	}
}