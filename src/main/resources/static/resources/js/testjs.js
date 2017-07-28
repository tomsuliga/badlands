/**
 * 
 */
// jshint esversion:6
// jshint evil:true

/* Long comment */
// Short comment
var today = new Date();
var hourNow = today.getHours();
var greeting;

if (hourNow > 18) {
	greeting = 'Good Evening';
} else if (hourNow > 12) {
	greeting = 'Good Afternoon';
} else if (hourNow > 0) {
	greeting = 'Good Morning';
} else {
	greeting = 'Hello Sometime';
}

document.write('<h2>' + greeting + '</h2>');

var a = 1; // function scope
let b = 2; // block scope
const c = 3; // block scope & final
a=10;
b=20;
// c=30; // Error

doit1();
console.log('3x7=' + multiply(3,7));

function doit1() {
	var a1 = ['one','two','three'];
	var a2 = new Array('one','two','three');
	
	document.write('<br>');
	document.write(a1);
	document.write('<br>');
	document.write(a2);
	
	console.log(a1[1]); // two
	console.log(a2[2]); // three
}

function multiply(width, height) {
	const result = width * height;
	return result;
}

// immediately invoked function
var f1 = (function() {
	console.log('I am running now.');
}());

// Creating an object literal
var hotel = {
	name: 'SleepyMoon',
	rooms: 40,
	booked: 35,
	checkAvailable: function() {
		return this.rooms - this.booked;
	}
};

// Creating object class
function Hotel(name, rooms) {
	this.name = name;
	this.rooms = rooms;
	this.display = function() {
		console.log(name + ':' + rooms);
	};
}

var hotel1 = new Hotel("Acme", 50);
var hotel2 = new Hotel('Solar', 100);

hotel1.display();
hotel2.display();

window.console.log('console is from window');

document.getElementById("btn123").onclick = function () { 
	let v1 = document.getElementById('span123');
	//v1.innerHTML = "sometext"; //INSECURE!!
	v1.textContent = 'My new span text';
	console.log(Object.getOwnPropertyNames(v1));
};

document.getElementById('btnrandom').onclick = function() {
	const e1 = document.getElementById('spanrandom');
	const r = Math.floor((Math.random() * 100) + 1);
	e1.textContent = r;
	let msg = '';
	if (r===100) {
		msg = 'Perfect!';
	} else if (r >= 75) {
		msg = 'Great';
	} else if (r >= 25) {
		msg = 'Good';
	} else {
		msg = 'Poor';
	}
	document.getElementById('spanmsg').textContent = msg;
};
/*
var test123 = 'B';
switch (test123) {
case 'A': alert('aaa'); break;
case 'B': alert('bbb'); break;
case 'C': alert('ccc'); break;
default: alert('unknown');
}
*/

for (var i=0;i<5;i++) {
	console.log('i=' + i); // 0 1 2 3 4
}

{
var i2 = 0;
while (i2<5) {
	console.log('i2=' + i2);
	i2++;
}
}

{
let i3 = 0;
do {
	console.log('i3=' + i3);
	i3++;
} while(i3 < 5);
}

document.getElementById('someId'); // single
document.querySelector('ul.li'); // first element
document.getElementsByClassName('someClass'); // 1 or more
document.getElementsByTagName('li'); // 1 or more
document.querySelectorAll('ul.li'); // 1 or more

var elements = document.getElementsByClassName('bogus');
if (elements.length >= 1) {
	var firstElement = elements[0];
	//alert('first element = ' + firstElement);
} else {
	//alert('no elements for class');
}

document.getElementById('btnNewGame').onclick = function() {
	let eDivGrid = document.getElementById('divGrid');
	// remove old
	while (eDivGrid.hasChildNodes()) {
		eDivGrid.removeChild(eDivGrid.lastChild);
	}
	// add new
	for (let row=0;row<8;row++) {
		for (let col=0;col<16;col++) {
			let e = document.createElement('div');
			e.setAttribute('class', 'gridClass green');
			e.setAttribute('id', row + ':' + col);
			eDivGrid.appendChild(e);
		}
	}
	
	document.getElementById('4:4').classList.remove('green');
	document.getElementById('4:4').classList.add('red');
	document.getElementById('5:5').classList.toggle('green');
	document.getElementById('5:5').classList.toggle('blue');
};

// capture mouse click for any div grid class
document.body.onclick = function(e) {
	e = window.event ? event.srcElement : e.target;
	if (e.className && e.className.indexOf('gridClass')!=-1) {
		console.log('clicked ' + e.id);
	}
};

// temp
document.body.onkeyup = function(evt) {
	evt = evt || window.event;
    console.log('onkeyup: ' + String.fromCharCode(evt.keyCode));
};








