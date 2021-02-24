/* 
 * reportScreen.js contain javascript functions to send request to backend
 * 
 * @author Nhu Phan
 */
<script language="Javascript">

/* 
 * Send a request to print a pdf of the daily production report 
 */
function printDailyReportPDF() 
{
    window.location="/Capstone/reportServices?action=print";
    alert("Your daily report is now printing.");
}


/* 
 * Send a request to get the previous production report 
 */
function getPreviousProductionReport(date) 
{
    window.location="/Capstone/reportServices?getPreviousReport=true&dailyReportDate=" + date;
}

/* 
 * Send a request to get the next day production report 
 */
function getNextProductionReport(date) 
{
    window.location="/Capstone/reportServices?getNextDailyReport=true&dailyReportDate=" + date;
}

/*
 * Send a request to get a sorted production report 
 */
function getSortedProductionReport(date) 
{
    // TODO need to implement

    window.location="/Capstone/reportServices?sortDailyReport=true&dailyReportDate=" + date;
}

/* 
 * TODO: finish function
 */
function getStartDate(date) {

}

/* 
 * TODO: finish function
 */
function getEndDate(date) {

}

/* 
 * Send a request to get a weekly production report 
 */
function getWeeklyProductionReport(date) 
{
    let startDate = getStartDate(date);
    let endDate = getEndDate(date);
    window.location="/Capstone/reportServices?getWeeklyReport=true&dailystartDate=" + startdate + "&endDate=" + endDate;
}


/* 
 * Send a request to get a monthly production report 
 */
function getMonthlyProductionReport(date) 
{
    // TODO, write code to get the month from date
    let month = "february";

    window.location="/Capstone/reportServices?getMonthlyReport=true&month=" + month;
}

</script>

