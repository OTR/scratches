var entity = window.localStorage
entity.setItem(
    "https://smtebooks.com/book/424242",
    "{'title': 'Definite Guide into Python', 'authors': ['Mark Lutz', 'David Beazley']}")
entity.length

function powering (i) {
    let _str = "";
    for (let _ = 0; _ < i; _++) {
        _str += "А";
    };
    return _str;
};


var dbEng = window.indexedDB ||
window.webkitIndexedDB || // Chrome
window.mozIndexedDB || // Firefox
window.msIndexedDB; // IE
var db; // This is a handle to the database
if (!dbEng) {
    alert("IndexedDB is not supported on this browser");
} else {
    var request = dbEng.open("some_key", 1);
    request.onsuccess = function (event) {
        db = event.target.result;
    };
    request.onerror = function (event) {
        alert("Please allow the browser to open the database");
    };
    request.onupgradeneeded = function (event) {
        configureDatabase(event);
    }
}

entity.setItem("0", powering(5 * 10 ** 6)); // -> undefined
entity.getItem("0").length // 5000000

entity.setItem("0", powering(5500000));
entity.setItem("0", powering(5242766)); //5242848
// 5242880

// Maximum allowed space to store a value in localStorage is
// (5*1024*1024 - 1) letters (1 letter for a minimal key)
// Each letter is in Unicode, that means 4 Bytes per letter
// So all the keys and all the values in bytes shouldn't exceed
// 4*(5*1024*1024*4) which equals 20 MiB - 4 Bytes
// So now we need some serialization algorithm that turn sequence of bytes into
// sequence of unicode letters

entity.setItem("K", powering(5 * 1024 * 1024 - 1))