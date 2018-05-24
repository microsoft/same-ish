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
var args__23329__auto__ = [];
var len__23326__auto___135 = arguments.length;
var i__23327__auto___136 = (0);
while(true){
if((i__23327__auto___136 < len__23326__auto___135)){
args__23329__auto__.push((arguments[i__23327__auto___136]));

var G__137 = (i__23327__auto___136 + (1));
i__23327__auto___136 = G__137;
continue;
} else {
}
break;
}

var argseq__23330__auto__ = ((((1) < args__23329__auto__.length))?(new cljs.core.IndexedSeq(args__23329__auto__.slice((1)),(0),null)):null);
return same$macros.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__23330__auto__);
});

same$macros.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (expected,actuals){
if(cljs.core.truth_(cljs.core.not_empty.call(null,actuals))){
} else {
throw (new Error("Assert failed: (not-empty actuals)"));
}

return cljs.core.every_QMARK_.call(null,cljs.core.partial.call(null,same.ish.ish,expected),actuals);
});

same$macros.ish_QMARK_.cljs$lang$maxFixedArity = (1);

same$macros.ish_QMARK_.cljs$lang$applyTo = (function (seq133){
var G__134 = cljs.core.first.call(null,seq133);
var seq133__$1 = cljs.core.next.call(null,seq133);
return same$macros.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic(G__134,seq133__$1);
});

/**
 * Compare a numeric value to zero, returning true if close.
 *   ```klipse
 *   (zeroish? 0.0000000001
 *          :max-diff 1e6)
 *   ```
 */
same$macros.zeroish_QMARK_ = (function same$macros$zeroish_QMARK_(var_args){
var args__23329__auto__ = [];
var len__23326__auto___143 = arguments.length;
var i__23327__auto___144 = (0);
while(true){
if((i__23327__auto___144 < len__23326__auto___143)){
args__23329__auto__.push((arguments[i__23327__auto___144]));

var G__145 = (i__23327__auto___144 + (1));
i__23327__auto___144 = G__145;
continue;
} else {
}
break;
}

var argseq__23330__auto__ = ((((1) < args__23329__auto__.length))?(new cljs.core.IndexedSeq(args__23329__auto__.slice((1)),(0),null)):null);
return same$macros.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__23330__auto__);
});

same$macros.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__140){
var map__141 = p__140;
var map__141__$1 = ((((!((map__141 == null)))?((((map__141.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__141.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__141):map__141);
var max_diff = cljs.core.get.call(null,map__141__$1,new cljs.core.Keyword(null,"max-diff","max-diff",(1616818640)),(1000));
return same.compare.near_zero.call(null,val,max_diff);
});

same$macros.zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

same$macros.zeroish_QMARK_.cljs$lang$applyTo = (function (seq138){
var G__139 = cljs.core.first.call(null,seq138);
var seq138__$1 = cljs.core.next.call(null,seq138);
return same$macros.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic(G__139,seq138__$1);
});

/**
 * Compare a numeric value to zero, returning true if not close. Equivalent to `(not (zeroish? ...))`.
 *   ```klipse
 *   (not-zeroish? 3 :max-diff 1e6)
 *   ```
 */
same$macros.not_zeroish_QMARK_ = (function same$macros$not_zeroish_QMARK_(var_args){
var args__23329__auto__ = [];
var len__23326__auto___151 = arguments.length;
var i__23327__auto___152 = (0);
while(true){
if((i__23327__auto___152 < len__23326__auto___151)){
args__23329__auto__.push((arguments[i__23327__auto___152]));

var G__153 = (i__23327__auto___152 + (1));
i__23327__auto___152 = G__153;
continue;
} else {
}
break;
}

var argseq__23330__auto__ = ((((1) < args__23329__auto__.length))?(new cljs.core.IndexedSeq(args__23329__auto__.slice((1)),(0),null)):null);
return same$macros.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__23330__auto__);
});

same$macros.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__148){
var map__149 = p__148;
var map__149__$1 = ((((!((map__149 == null)))?((((map__149.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__149.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__149):map__149);
var max_diff = cljs.core.get.call(null,map__149__$1,new cljs.core.Keyword(null,"max-diff","max-diff",(1616818640)),(1000));
return cljs.core.not.call(null,same.compare.near_zero.call(null,val,max_diff));
});

same$macros.not_zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

same$macros.not_zeroish_QMARK_.cljs$lang$applyTo = (function (seq146){
var G__147 = cljs.core.first.call(null,seq146);
var seq146__$1 = cljs.core.next.call(null,seq146);
return same$macros.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic(G__147,seq146__$1);
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
var ret__23352__auto___158 = (function (){
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
var args__23329__auto__ = [];
var len__23326__auto___159 = arguments.length;
var i__23327__auto___160 = (0);
while(true){
if((i__23327__auto___160 < len__23326__auto___159)){
args__23329__auto__.push((arguments[i__23327__auto___160]));

var G__161 = (i__23327__auto___160 + (1));
i__23327__auto___160 = G__161;
continue;
} else {
}
break;
}

var argseq__23330__auto__ = ((((3) < args__23329__auto__.length))?(new cljs.core.IndexedSeq(args__23329__auto__.slice((3)),(0),null)):null);
return same$macros.with_comparator.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),argseq__23330__auto__);
});

same$macros.with_comparator.cljs$core$IFn$_invoke$arity$variadic = (function (_AMPERSAND_form,_AMPERSAND_env,comparator,body){
return cljs.core.sequence.call(null,cljs.core.concat.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,new cljs.core.Symbol("cljs.core","binding","cljs.core/binding",(2050379843),null)),(function (){var x__23030__auto__ = cljs.core.vec.call(null,cljs.core.sequence.call(null,cljs.core.concat.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,new cljs.core.Symbol("same.ish","*comparator*","same.ish/*comparator*",(-1083476129),null)),(function (){var x__23030__auto__ = comparator;
return cljs.core._conj.call(null,cljs.core.List.EMPTY,x__23030__auto__);
})())));
return cljs.core._conj.call(null,cljs.core.List.EMPTY,x__23030__auto__);
})(),body));
});

same$macros.with_comparator.cljs$lang$maxFixedArity = (3);

same$macros.with_comparator.cljs$lang$applyTo = (function (seq154){
var G__155 = cljs.core.first.call(null,seq154);
var seq154__$1 = cljs.core.next.call(null,seq154);
var G__156 = cljs.core.first.call(null,seq154__$1);
var seq154__$2 = cljs.core.next.call(null,seq154__$1);
var G__157 = cljs.core.first.call(null,seq154__$2);
var seq154__$3 = cljs.core.next.call(null,seq154__$2);
return same$macros.with_comparator.cljs$core$IFn$_invoke$arity$variadic(G__155,G__156,G__157,seq154__$3);
});

return null;
})()
;
same$macros.with_comparator.cljs$lang$macro = true;

