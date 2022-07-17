goog.provide("same.compare");
same.compare.near_zero_double = (function same$compare$near_zero_double(f,scale){
return (Math.abs(f) <= same.platform.ulp.call(null,scale));
});
/**
 * Test if a number is near zero.
 */
same.compare.near_zero = (function same$compare$near_zero(f,scale){
return same.compare.near_zero_double.call(null,f,scale);
});
same.compare.compare_ulp_double = (function same$compare$compare_ulp_double(f1,f2,max_abs,max_ulp){
var f1__$1 = f1;
var f2__$1 = f2;
if((Math.abs((f1__$1 - f2__$1)) <= max_abs)){
return true;
} else {
if(cljs.core.not_EQ_.call(null,same.platform.sign.call(null,f1__$1),same.platform.sign.call(null,f2__$1))){
return false;
} else {
if(cljs.core.not_EQ_.call(null,same.platform.is_infinite_QMARK_.call(null,f1__$1),same.platform.is_infinite_QMARK_.call(null,f2__$1))){
return false;
} else {
return (same.platform.bit_diff_double.call(null,f1__$1,f2__$1) <= cljs.core.long$.call(null,max_ulp));

}
}
}
});
/**
 * Create a comparator function that compares numbers by ULPs.
 */
same.compare.compare_ulp = (function same$compare$compare_ulp(scale,max_ulp){
var max_abs = same.platform.ulp.call(null,scale);
return ((function (max_abs){
return (function (f1,f2){
return same.compare.compare_ulp_double.call(null,f1,f2,max_abs,max_ulp);
});
;})(max_abs))
});
