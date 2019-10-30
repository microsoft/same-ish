goog.provide("same$macros");
/**
 * Compare one or more values to an expected value, returning true if they are the same-ish.
 *   The values can be numbers:
 *   ```klipse
 *   (let [two (Math/pow (Math/sqrt 2) 2)]
 *  [(== 2 two) (ish? 2 two)])
 *   ```
 *   or data structures:
 *   ```klipse
 *   (ish? {:a 1 :b [1.99999999999999 3]}
 *      {:a 1.00000000000001 :b [2 3.0]})
 *   ```
 *   you can also compare more than one value to the expected value:
 *   ```klipse
 *   (ish? 1 1.0 0.99999999999999 1.00000000000001 1)
 *   ```
 */
same$macros.ish_QMARK_ = (function same$macros$ish_QMARK_(var_args){
var args__10338__auto__ = [];
var len__10335__auto___150 = arguments.length;
var i__10336__auto___151 = (0);
while(true){
if((i__10336__auto___151 < len__10335__auto___150)){
args__10338__auto__.push((arguments[i__10336__auto___151]));

var G__152 = (i__10336__auto___151 + (1));
i__10336__auto___151 = G__152;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((1) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((1)),(0),null)):null);
return same$macros.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__10339__auto__);
});

same$macros.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (expected,actuals){
if(cljs.core.truth_(cljs.core.not_empty.call(null,actuals))){
} else {
throw (new Error("Assert failed: (not-empty actuals)"));
}

return cljs.core.every_QMARK_.call(null,cljs.core.partial.call(null,same.ish.ish,expected),actuals);
});

same$macros.ish_QMARK_.cljs$lang$maxFixedArity = (1);

/** @this {Function} */
same$macros.ish_QMARK_.cljs$lang$applyTo = (function (seq148){
var G__149 = cljs.core.first.call(null,seq148);
var seq148__$1 = cljs.core.next.call(null,seq148);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__149,seq148__$1);
});

/**
 * Compare a numeric value to zero, returning true if close.
 *   ```klipse
 *   (zeroish? 0.0000000001
 *          :max-diff 1e6)
 *   ```
 */
same$macros.zeroish_QMARK_ = (function same$macros$zeroish_QMARK_(var_args){
var args__10338__auto__ = [];
var len__10335__auto___158 = arguments.length;
var i__10336__auto___159 = (0);
while(true){
if((i__10336__auto___159 < len__10335__auto___158)){
args__10338__auto__.push((arguments[i__10336__auto___159]));

var G__160 = (i__10336__auto___159 + (1));
i__10336__auto___159 = G__160;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((1) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((1)),(0),null)):null);
return same$macros.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__10339__auto__);
});

same$macros.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__155){
var map__156 = p__155;
var map__156__$1 = (((((!((map__156 == null))))?(((((map__156.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__156.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__156):map__156);
var max_diff = cljs.core.get.call(null,map__156__$1,new cljs.core.Keyword(null,"max-diff","max-diff",(1616818640)),(1000));
return same.compare.near_zero.call(null,val,max_diff);
});

same$macros.zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

/** @this {Function} */
same$macros.zeroish_QMARK_.cljs$lang$applyTo = (function (seq153){
var G__154 = cljs.core.first.call(null,seq153);
var seq153__$1 = cljs.core.next.call(null,seq153);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__154,seq153__$1);
});

/**
 * Compare a numeric value to zero, returning true if not close. Equivalent to `(not (zeroish? ...))`.
 *   ```klipse
 *   (not-zeroish? 3 :max-diff 1e6)
 *   ```
 */
same$macros.not_zeroish_QMARK_ = (function same$macros$not_zeroish_QMARK_(var_args){
var args__10338__auto__ = [];
var len__10335__auto___166 = arguments.length;
var i__10336__auto___167 = (0);
while(true){
if((i__10336__auto___167 < len__10335__auto___166)){
args__10338__auto__.push((arguments[i__10336__auto___167]));

var G__168 = (i__10336__auto___167 + (1));
i__10336__auto___167 = G__168;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((1) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((1)),(0),null)):null);
return same$macros.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__10339__auto__);
});

same$macros.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__163){
var map__164 = p__163;
var map__164__$1 = (((((!((map__164 == null))))?(((((map__164.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__164.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__164):map__164);
var max_diff = cljs.core.get.call(null,map__164__$1,new cljs.core.Keyword(null,"max-diff","max-diff",(1616818640)),(1000));
return (!(same.compare.near_zero.call(null,val,max_diff)));
});

same$macros.not_zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

/** @this {Function} */
same$macros.not_zeroish_QMARK_.cljs$lang$applyTo = (function (seq161){
var G__162 = cljs.core.first.call(null,seq161);
var seq161__$1 = cljs.core.next.call(null,seq161);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__162,seq161__$1);
});

/**
 * Set the default comparator.
 *   ```klipse
 *   (set-comparator! (compare-ulp 2.0 100))
 *   (ish? 0.1 (-> 2 Math/sqrt (Math/pow 2) (- 1.9)))
 *   ```
 */
same$macros.set_comparator_BANG_ = (function same$macros$set_comparator_BANG_(comparator){
same.ish._STAR_comparator_STAR_ = comparator;

return null;
});
var ret__10368__auto___173 = (function (){
/**
 * Temporarily replace the default comparator.
 *   ```klipse
 *   (with-comparator (compare-ulp 100.0 1e9)
 *  (ish? 1.0 0.9999999))
 *   ```
 *   ```klipse
 *   (with-comparator ==
 *  (ish? 1.0 0.9999999999999))
 *   ```
 */
same$macros.with_comparator = (function same$macros$with_comparator(var_args){
var args__10338__auto__ = [];
var len__10335__auto___174 = arguments.length;
var i__10336__auto___175 = (0);
while(true){
if((i__10336__auto___175 < len__10335__auto___174)){
args__10338__auto__.push((arguments[i__10336__auto___175]));

var G__176 = (i__10336__auto___175 + (1));
i__10336__auto___175 = G__176;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((3) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((3)),(0),null)):null);
return same$macros.with_comparator.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),argseq__10339__auto__);
});

same$macros.with_comparator.cljs$core$IFn$_invoke$arity$variadic = (function (_AMPERSAND_form,_AMPERSAND_env,comparator,body){
return cljs.core.sequence.call(null,cljs.core.concat.call(null,(new cljs.core.List(null,new cljs.core.Symbol("cljs.core","binding","cljs.core/binding",(2050379843),null),null,(1),null)),(new cljs.core.List(null,cljs.core.vec.call(null,cljs.core.sequence.call(null,cljs.core.concat.call(null,(new cljs.core.List(null,new cljs.core.Symbol("same.ish","*comparator*","same.ish/*comparator*",(-1083476129),null),null,(1),null)),(new cljs.core.List(null,comparator,null,(1),null))))),null,(1),null)),body));
});

same$macros.with_comparator.cljs$lang$maxFixedArity = (3);

/** @this {Function} */
same$macros.with_comparator.cljs$lang$applyTo = (function (seq169){
var G__170 = cljs.core.first.call(null,seq169);
var seq169__$1 = cljs.core.next.call(null,seq169);
var G__171 = cljs.core.first.call(null,seq169__$1);
var seq169__$2 = cljs.core.next.call(null,seq169__$1);
var G__172 = cljs.core.first.call(null,seq169__$2);
var seq169__$3 = cljs.core.next.call(null,seq169__$2);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__170,G__171,G__172,seq169__$3);
});

return null;
})()
;
same$macros.with_comparator.cljs$lang$macro = true;

