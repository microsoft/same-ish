goog.provide("same");
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
same.ish_QMARK_ = (function same$ish_QMARK_(var_args){
var args__23329__auto__ = [];
var len__23326__auto___164 = arguments.length;
var i__23327__auto___165 = (0);
while(true){
if((i__23327__auto___165 < len__23326__auto___164)){
args__23329__auto__.push((arguments[i__23327__auto___165]));

var G__166 = (i__23327__auto___165 + (1));
i__23327__auto___165 = G__166;
continue;
} else {
}
break;
}

var argseq__23330__auto__ = ((((1) < args__23329__auto__.length))?(new cljs.core.IndexedSeq(args__23329__auto__.slice((1)),(0),null)):null);
return same.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__23330__auto__);
});

same.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (expected,actuals){
if(cljs.core.truth_(cljs.core.not_empty.call(null,actuals))){
} else {
throw (new Error("Assert failed: (not-empty actuals)"));
}

return cljs.core.every_QMARK_.call(null,cljs.core.partial.call(null,same.ish.ish,expected),actuals);
});

same.ish_QMARK_.cljs$lang$maxFixedArity = (1);

same.ish_QMARK_.cljs$lang$applyTo = (function (seq162){
var G__163 = cljs.core.first.call(null,seq162);
var seq162__$1 = cljs.core.next.call(null,seq162);
return same.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic(G__163,seq162__$1);
});

/**
 * Compare a numeric value to zero, returning true if close.
 *   ```klipse
 *   (zeroish? 0.0000000001
 *          :max-diff 1e6)
 *   ```
 */
same.zeroish_QMARK_ = (function same$zeroish_QMARK_(var_args){
var args__23329__auto__ = [];
var len__23326__auto___172 = arguments.length;
var i__23327__auto___173 = (0);
while(true){
if((i__23327__auto___173 < len__23326__auto___172)){
args__23329__auto__.push((arguments[i__23327__auto___173]));

var G__174 = (i__23327__auto___173 + (1));
i__23327__auto___173 = G__174;
continue;
} else {
}
break;
}

var argseq__23330__auto__ = ((((1) < args__23329__auto__.length))?(new cljs.core.IndexedSeq(args__23329__auto__.slice((1)),(0),null)):null);
return same.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__23330__auto__);
});

same.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__169){
var map__170 = p__169;
var map__170__$1 = ((((!((map__170 == null)))?((((map__170.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__170.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__170):map__170);
var max_diff = cljs.core.get.call(null,map__170__$1,new cljs.core.Keyword(null,"max-diff","max-diff",(1616818640)),(1000));
return same.compare.near_zero.call(null,val,max_diff);
});

same.zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

same.zeroish_QMARK_.cljs$lang$applyTo = (function (seq167){
var G__168 = cljs.core.first.call(null,seq167);
var seq167__$1 = cljs.core.next.call(null,seq167);
return same.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic(G__168,seq167__$1);
});

/**
 * Compare a numeric value to zero, returning true if not close. Equivalent to `(not (zeroish? ...))`.
 *   ```klipse
 *   (not-zeroish? 3 :max-diff 1e6)
 *   ```
 */
same.not_zeroish_QMARK_ = (function same$not_zeroish_QMARK_(var_args){
var args__23329__auto__ = [];
var len__23326__auto___180 = arguments.length;
var i__23327__auto___181 = (0);
while(true){
if((i__23327__auto___181 < len__23326__auto___180)){
args__23329__auto__.push((arguments[i__23327__auto___181]));

var G__182 = (i__23327__auto___181 + (1));
i__23327__auto___181 = G__182;
continue;
} else {
}
break;
}

var argseq__23330__auto__ = ((((1) < args__23329__auto__.length))?(new cljs.core.IndexedSeq(args__23329__auto__.slice((1)),(0),null)):null);
return same.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__23330__auto__);
});

same.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__177){
var map__178 = p__177;
var map__178__$1 = ((((!((map__178 == null)))?((((map__178.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__178.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__178):map__178);
var max_diff = cljs.core.get.call(null,map__178__$1,new cljs.core.Keyword(null,"max-diff","max-diff",(1616818640)),(1000));
return cljs.core.not.call(null,same.compare.near_zero.call(null,val,max_diff));
});

same.not_zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

same.not_zeroish_QMARK_.cljs$lang$applyTo = (function (seq175){
var G__176 = cljs.core.first.call(null,seq175);
var seq175__$1 = cljs.core.next.call(null,seq175);
return same.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic(G__176,seq175__$1);
});

/**
 * Set the default comparator.
 *   ```klipse
 *   (set-comparator! (compare-ulp 2.0 100))
 *   (ish? 0.1 (-> 2 Math/sqrt (Math/pow 2) (- 1.9)))
 *   ```
 */
same.set_comparator_BANG_ = (function same$set_comparator_BANG_(comparator){
same.ish._STAR_comparator_STAR_ = comparator;

return null;
});
var ret__23352__auto___187 = (function (){
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
same.with_comparator = (function same$with_comparator(var_args){
var args__23329__auto__ = [];
var len__23326__auto___188 = arguments.length;
var i__23327__auto___189 = (0);
while(true){
if((i__23327__auto___189 < len__23326__auto___188)){
args__23329__auto__.push((arguments[i__23327__auto___189]));

var G__190 = (i__23327__auto___189 + (1));
i__23327__auto___189 = G__190;
continue;
} else {
}
break;
}

var argseq__23330__auto__ = ((((3) < args__23329__auto__.length))?(new cljs.core.IndexedSeq(args__23329__auto__.slice((3)),(0),null)):null);
return same.with_comparator.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),argseq__23330__auto__);
});

same.with_comparator.cljs$core$IFn$_invoke$arity$variadic = (function (_AMPERSAND_form,_AMPERSAND_env,comparator,body){
return cljs.core.sequence.call(null,cljs.core.concat.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,new cljs.core.Symbol("cljs.core","binding","cljs.core/binding",(2050379843),null)),(function (){var x__23030__auto__ = cljs.core.vec.call(null,cljs.core.sequence.call(null,cljs.core.concat.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,new cljs.core.Symbol("same.ish","*comparator*","same.ish/*comparator*",(-1083476129),null)),(function (){var x__23030__auto__ = comparator;
return cljs.core._conj.call(null,cljs.core.List.EMPTY,x__23030__auto__);
})())));
return cljs.core._conj.call(null,cljs.core.List.EMPTY,x__23030__auto__);
})(),body));
});

same.with_comparator.cljs$lang$maxFixedArity = (3);

same.with_comparator.cljs$lang$applyTo = (function (seq183){
var G__184 = cljs.core.first.call(null,seq183);
var seq183__$1 = cljs.core.next.call(null,seq183);
var G__185 = cljs.core.first.call(null,seq183__$1);
var seq183__$2 = cljs.core.next.call(null,seq183__$1);
var G__186 = cljs.core.first.call(null,seq183__$2);
var seq183__$3 = cljs.core.next.call(null,seq183__$2);
return same.with_comparator.cljs$core$IFn$_invoke$arity$variadic(G__184,G__185,G__186,seq183__$3);
});

return null;
})()
;
same.with_comparator.cljs$lang$macro = true;

