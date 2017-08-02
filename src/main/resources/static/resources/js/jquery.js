/*globals $:false */
/*globals window:false */
/*globals jQuery:false */
/*globals console:false */
/*globals alert:false */
/*jshint esversion:6 */
/*jshint evil:true */
/*jshint globalstrict: true */
'use strict';

$('document').ready(function() {
	console.log('doc ready');
	$("p#p9").attr("class", "italic red");
	$("p#p9").on("mouseover", 
			function() {	
		//$(this).text("now " + $(this).text()); 
		//$(this).append(" at the end.");
		}
	);
	
	$("a#a99").on("click", function() {
		$("p#p99").toggleClass("full");
		//$("p#p99").animate( {height: "10em"} );
		//$("p#p99").animate({right: "15%"});
		// use Delay(millis) to delay an action
		$("p#p99").delay(1000).animate({top: "100px", left: "300px"});
		var wh = $(window).height();
		$("p#p99").append("wh=" + wh);
		var e = $(this);
		// stop current animation
		e.stop();
		// stop current and future animations
		e.stop(true);
		// quickly complete current animation and stop future animations
		e.stop(true, true);		
	});
	
	//$(this).fadeTo(millis, opacity, optional-callback-function)
	//$(this).fadeTo(3000, 1); // assume from 0% to 100%
	//$(this).fadeTo(2000, 0, function() {
	//	console.log("image now gone");
	//});
	
	//$("p")
	
	//var hDiv = $("div#info").outerHeight(true);
	
	$(this).animate({marginBottom: "+=10%"});
	$("img#dog").animate({marginLeft: "+=100px"});
	
	var h = $(this).height();
	$(this).animate({marginTop: "-=" + h + "px"});
	
	// don't use 300px
	$(this).attr("width", "300");
	$(this).removeAttr("height");
	$(this).removeAttr("width").removeAttr("height");
	$("img#cat, img#dog").removeAttr("width");
	$(this).attr("class", "big bright");
	$("button#plain_text").on("click", function() {
		  $("p").removeAttr("class");
	});
	//$("textarea").attr("rows", "8").attr("cols", "100");
	
	//$("textarea").on("focus", function() {
	//	  $(this).removeAttr("rows").removeAttr("cols");
	//});
	
	// removes temp div
	$("div#temp").before("<div></div>").remove();
	
	$("p.hype").html("<h3>New!</h3>");
	
	$("img").on("click", function() {
		$(this).attr("src", "loris.jpg");
	});
	
	$("div#d1").after("<div id='d2'></div>");
	
	$("input#id1").on("focus", function() {
		  $("form#id0").prepend("<p>hello</p>");
	});
	
	$("p#id1").empty().append("hi");
	
	$("body").prepend("<div></div>");
	
	$("p#pets").prepend("<img src='loris.jpg'>");
	
	$("body").prepend("<div id='pets'></div>");
	$("div#pets").prepend("<p></p>");
	
	$("button#btntog").on("click", function() {
		$("img").toggleClass("invisible");
	});
	
	$("button#btnreplace").on("click", function() {
		$("p#preplace").replaceWith("<p id='preplace'><i>Italic para</i></p>");
	});
	
	$("p:contains('space')").toggleClass("red");
	
	var name = 'Tom';
	$("div:contains(name)").toggleClass("blue");
	
	var head = $("h2");
	
	$("form#f1").on("submit", function() {
		  var fn = $("input#name_first").val();
	});
	
	// opposite of focus is blur
	$("input#formal").on("blur", function() {
		  $(this).val("Ms. " + $(this).val());
	});
	
	$("div#main").css("color", "black").css("font-size", "1.5em");
	
	$("p#id1").toggle().attr("class", "red");
	
	$("input#id1").attr("id", "id2").val("abc");
	
	$("h3#someId").replaceWith("<h2>Hello there.</h2>");
});

$("p#a").on("mouseover", function() {
	  $(this).toggleClass("b");
});
$("p#a").on("mouseout", function() {
	  $(this).toggleClass("b");
});
	
$('#changeGreeting').click(function() {
	$('#greeting').text('Hello My Friend');
});

$( "#fadein" ).click(function() {
	  $( "#ww" ).fadeIn( "slow", function() {
	    // Animation complete
	  });
	  $( "#ww" ).fadeIn( "slow");
	  $( "#ww2" ).fadeIn();
	  $( "#ww3" ).fadeIn( "fast");
	  //$( "#ww4" ).fadeIn(5000);
});

$( "#fadeout" ).click(function() {
	  $( "#ww" ).fadeOut( "slow", function() {
	    // Animation complete
	  });
	  $("img#ww2").fadeOut();
	  $("img#ww3").fadeOut("fast");
});

$('#hideall').click(function() {
	$('img').hide();
	window.$('img').hide();
	jQuery('img').hide();
	window.jQuery('img').hide();
});

$('#test1').click(function() {
	$('p').addClass('big blue');
	var first_name = $("input#firstName").val();
	alert("fn = " + first_name);
});

//map()
let names = [ 'tom', 'tommy', 'thomas'];
names = names.map(x => x.charAt(0).toUpperCase() + x.slice(1));
console.log('names=' + names); // names=Tom,Tommy,Thomas

$('button#myid').on('click', function() { // faster
	console.log('here');
});
$('button#myid').click(function() { // slower
	console.log('here');
});

/* Also valid:
	'dblclick'
	'focus'
	'submit'
	'click dblclick'
*/

// When specifying the submit button in a form, you can select the form itself:
$('form#myid').on('submit', function() {
	console.log('here');
});

// user has entered a text field in a form
$("input#myid").on("focus", function() {
	console.log("here");
});

$("button#slideDown").on("click", function() {
	$("img#ww3").slideDown("slow");
});

$("button#slideUp").on("click", function() {
	$("img#ww3").slideUp("slow");
});

$("p#myid").show("fast");
$("p#myid").hide("slow");
$("p#myid").toggle(3000);
$("p#myid").toggle();

$("input#myid1").on("focus", function() {
	$("p#myid2").show();
});

$("span#special").css("font-weight", "bold");
$("p#myid").css("color", "red");
$("div#flex").css("width", "45%");
//$("p").css({ "color":"red", "font-size":"2em"});

$("p").hover(function() {
	$(this).css("color", "green");
	console.log("hover");
});

$("p").on("click", function() {
	$(this).css("font-size", "2.5em");
});

$("img#myid").on("click", function() {
	$(this).css("border", "1px solid black");
});

$("p#id1, p#id2").on("click", function() {
	$("img").slideDown();
});

$("input#f1").add("input#f2").on("focus", function() {
	$("p#reminder").show();
});

// Change id
$("p#myid1").attr("id", "myid2");

$("img").on("click", function() {
	$(this).remove();
});

// $() == jQuery()

// hide an image $("img#id1").hide();

$("div#dummy").on("click", function() {
	$(this).remove(); // remove element from DOM tree
	$(this).detach(); // same as remove() but also keeps a copy in memory
	$(this).empty(); // removes child nodes
	$(this).unwrap(); // removes parents
	$(this).clone(); // creates copy
	
	// on events:
	// "focus"  - element gains focus
	// "blur"   - element loses focus
	// "change" - value of input changes
	// "select" - option of a select element is changed
	// "submit" = when form is submitted
	// "click"
	// "dblclick"
});











