goog.provide("same.platform");
same.platform.max_value = Number.MAX_VALUE;
same.platform.infinity = Infinity;
/**
 * Return true if `a` is an array.
 */
same.platform.is_array_QMARK_ = (function same$platform$is_array_QMARK_(a){
return cljs.core.array_QMARK_.call(null,a);
});
/**
 * Return the sign of `f` (+1 if positive, -1 if negative, 0 if zero or NaN if NaN).
 */
same.platform.sign = (function same$platform$sign(f){
if((f < (0))){
return (-1);
} else {
if((f > (0))){
return (1);
} else {
if(cljs.core.not_EQ_.call(null,f,f)){
return f;
} else {
return (0);

}
}
}
});
/**
 * Return true if `f` is infinite.
 */
same.platform.is_infinite_QMARK_ = (function same$platform$is_infinite_QMARK_(f){
return cljs.core.infinite_QMARK_.call(null,f);
});
/**
 * Round `f` to a single precision (32-bit) float.
 */
same.platform.to_float = (function same$platform$to_float(f){
var arr = (new Float32Array((1)));
(arr[(0)] = f);

return (arr[(0)]);
});
same.platform.ulp_STAR_ = (function same$platform$ulp_STAR_(f){
var f__$1 = Math.abs(f);
var epsilon = Math.pow((2),(-52));
var max_value = (((2) - epsilon) * Math.pow((2),(1023)));
var max_ulp = Math.pow((2),(971));
if((f__$1 === (0))){
return (0);
} else {
if(cljs.core.not_EQ_.call(null,f__$1,f__$1)){
return f__$1;
} else {
if(cljs.core.truth_(same.platform.is_infinite_QMARK_.call(null,f__$1))){
return f__$1;
} else {
if(cljs.core._EQ_.call(null,max_value,f__$1)){
return max_ulp;
} else {
var buf = (new ArrayBuffer((8)));
var dv = (new DataView(buf));
var _ = dv.setFloat64((0),f__$1);
var hi = dv.getUint32((0));
var lo = dv.getUint32((4));
var ___$1 = dv.setUint32((4),(lo + (1)));
var ___$2 = ((cljs.core._EQ_.call(null,lo,(4294967295)))?dv.setUint32((0),(hi + (1))):null);
return (dv.getFloat64((0)) - f__$1);

}
}
}
}
});
/**
 * Units in the Last Place (ULP) of `f` (difference between f and the next largest representable number).
 */
same.platform.ulp = (function same$platform$ulp(f){
return same.platform.ulp_STAR_.call(null,f);
});
/**
 * Difference between two doubles in ULPs (i.e. number of representable numbers between them + 1).
 */
same.platform.bit_diff_double = (function same$platform$bit_diff_double(f1,f2){
var buf = (new ArrayBuffer((16)));
var dv = (new DataView(buf));
dv.setFloat64((0),f1);

dv.setFloat64((8),f2);

return Math.abs((((dv.getUint32((0)) - dv.getUint32((8))) * (4294967296)) + (dv.getUint32((4)) - dv.getUint32((12)))));
});
/**
 * Difference between two floats in ULPs (i.e. number of representable numbers between them + 1).
 */
same.platform.bit_diff_float = (function same$platform$bit_diff_float(f1,f2){
var buf = (new ArrayBuffer((8)));
var dv = (new DataView(buf));
dv.setFloat32((0),f1);

dv.setFloat32((4),f2);

return Math.abs((dv.getUint32((0)) - dv.getUint32((4))));
});
