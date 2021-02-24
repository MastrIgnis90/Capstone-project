/* 
 * reportScreen.js contain javascript functions to send request to backend
 * 
 * @author Nhu Phan
 */
<script language="Javascript">

/* 
 * Send a request to print a pdf of the daily report production
 */
function printDailyReportPDF() 
{
    window.location="/Capstone/reportServices?action=print";
    alert("Your daily report is now printing.");
}

</script>

