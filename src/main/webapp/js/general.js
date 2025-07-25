$(document).ready(function () {
    var interval = setInterval(function () {
        var momentNow = moment();
        $('#now-datetime').html('<i class="fa fa-clock-o"></i>' + momentNow.format('DD MMM YYYY') + ' | '
                .substring(0, 3).toUpperCase() + momentNow.format('hh:mm:ss A'));
    }, 500);

    if (document.location.pathname.indexOf("Welcome") === -1) {
        $("li:has(a[href='" + location.pathname + "'])").addClass('active');
        $("li:has(a[href='" + location.pathname + "'])").children('ul').addClass("in");
    }

});

function numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };

function numberWithCommasLead(x, lead) {
      return (x).toFixed(lead).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };

function percentFormat(x, lead) {
    return ((x * 100).toFixed(lead)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '%';
    };

function sortFunction(a, b) {
    if (a[0] === b[0]) {
        return 0;
    }
    else {
        return (a[0] < b[0]) ? -1 : 1;
    }
}

/* helper function to find date differences*/
function dateDiff(d1, d2) {
  return Math.floor((d2 - d1) / (1000 * 60 * 60 * 24));
}

Date.prototype.getWeek = function () {
	// Create a copy of this date object
	var target  = new Date(this.valueOf());

	// ISO week date weeks start on monday
	// so correct the day number
	var dayNr   = (this.getDay() + 6) % 7;

	// Set the target to the thursday of this week so the
	// target date is in the right year
	target.setDate(target.getDate() - dayNr + 3);

	// ISO 8601 states that week 1 is the week
	// with january 4th in it
	var jan4    = new Date(target.getFullYear(), 0, 4);

	// Number of days between target date and january 4th
	var dayDiff = (target - jan4) / 86400000;  

	// Calculate week number: Week 1 (january 4th) plus the  
	// number of weeks between target date and january 4th  
	var weekNr = 1 + Math.floor(dayDiff / 7);  // MLA removed "1 +"

	return weekNr;  
};

Date.prototype.nextWeek = function() {
  var copy = new Date(this.getTime());
  return new Date(copy.setDate(copy.getDate() + 7));
};

Date.prototype.beginningOfWeek = function() {
  var copy = new Date(this.getTime());
  var monday = new Date(copy.setDate(copy.getDate() - copy.getDay() + 1));
  monday.setHours(0);
  monday.setMinutes(0);
  monday.setSeconds(0);
  return monday;
};
