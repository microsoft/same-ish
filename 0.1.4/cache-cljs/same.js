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
var args__10338__auto__ = [];
var len__10335__auto___179 = arguments.length;
var i__10336__auto___180 = (0);
while(true){
if((i__10336__auto___180 < len__10335__auto___179)){
args__10338__auto__.push((arguments[i__10336__auto___180]));

var G__181 = (i__10336__auto___180 + (1));
i__10336__auto___180 = G__181;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((1) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((1)),(0),null)):null);
return same.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__10339__auto__);
});

same.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (expected,actuals){
if(cljs.core.truth_(cljs.core.not_empty.call(null,actuals))){
} else {
throw (new Error("Assert failed: (not-empty actuals)"));
}

return cljs.core.every_QMARK_.call(null,cljs.core.partial.call(null,same.ish.ish,expected),actuals);
});

same.ish_QMARK_.cljs$lang$maxFixedArity = (1);

/** @this {Function} */
same.ish_QMARK_.cljs$lang$applyTo = (function (seq177){
var G__178 = cljs.core.first.call(null,seq177);
var seq177__$1 = cljs.core.next.call(null,seq177);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__178,seq177__$1);
});

/**
 * Compare a numeric value to zero, returning true if close.
 *   ```klipse
 *   (zeroish? 0.0000000001
 *          :scale 1e6)
 *   ```
 */
same.zeroish_QMARK_ = (function same$zeroish_QMARK_(var_args){
var args__10338__auto__ = [];
var len__10335__auto___187 = arguments.length;
var i__10336__auto___188 = (0);
while(true){
if((i__10336__auto___188 < len__10335__auto___187)){
args__10338__auto__.push((arguments[i__10336__auto___188]));

var G__189 = (i__10336__auto___188 + (1));
i__10336__auto___188 = G__189;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((1) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((1)),(0),null)):null);
return same.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__10339__auto__);
});

same.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__184){
var map__185 = p__184;
var map__185__$1 = (((((!((map__185 == null))))?(((((map__185.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__185.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__185):map__185);
var scale = cljs.core.get.call(null,map__185__$1,new cljs.core.Keyword(null,"scale","scale",(-230427353)),(1000));
return same.compare.near_zero.call(null,val,scale);
});

same.zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

/** @this {Function} */
same.zeroish_QMARK_.cljs$lang$applyTo = (function (seq182){
var G__183 = cljs.core.first.call(null,seq182);
var seq182__$1 = cljs.core.next.call(null,seq182);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__183,seq182__$1);
});

/**
 * Compare a numeric value to zero, returning true if not close. Equivalent to `(not (zeroish? ...))`.
 *   ```klipse
 *   (not-zeroish? 3 :scale 1e6)
 *   ```
 */
same.not_zeroish_QMARK_ = (function same$not_zeroish_QMARK_(var_args){
var args__10338__auto__ = [];
var len__10335__auto___195 = arguments.length;
var i__10336__auto___196 = (0);
while(true){
if((i__10336__auto___196 < len__10335__auto___195)){
args__10338__auto__.push((arguments[i__10336__auto___196]));

var G__197 = (i__10336__auto___196 + (1));
i__10336__auto___196 = G__197;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((1) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((1)),(0),null)):null);
return same.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__10339__auto__);
});

same.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__192){
var map__193 = p__192;
var map__193__$1 = (((((!((map__193 == null))))?(((((map__193.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__193.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__193):map__193);
var scale = cljs.core.get.call(null,map__193__$1,new cljs.core.Keyword(null,"scale","scale",(-230427353)),(1000));
return (!(same.compare.near_zero.call(null,val,scale)));
});

same.not_zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

/** @this {Function} */
same.not_zeroish_QMARK_.cljs$lang$applyTo = (function (seq190){
var G__191 = cljs.core.first.call(null,seq190);
var seq190__$1 = cljs.core.next.call(null,seq190);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__191,seq190__$1);
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
var ret__10368__auto___202 = (function (){
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
var args__10338__auto__ = [];
var len__10335__auto___203 = arguments.length;
var i__10336__auto___204 = (0);
while(true){
if((i__10336__auto___204 < len__10335__auto___203)){
args__10338__auto__.push((arguments[i__10336__auto___204]));

var G__205 = (i__10336__auto___204 + (1));
i__10336__auto___204 = G__205;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((3) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((3)),(0),null)):null);
return same.with_comparator.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),argseq__10339__auto__);
});

same.with_comparator.cljs$core$IFn$_invoke$arity$variadic = (function (_AMPERSAND_form,_AMPERSAND_env,comparator,body){
return cljs.core.sequence.call(null,cljs.core.concat.call(null,(new cljs.core.List(null,new cljs.core.Symbol("cljs.core","binding","cljs.core/binding",(2050379843),null),null,(1),null)),(new cljs.core.List(null,cljs.core.vec.call(null,cljs.core.sequence.call(null,cljs.core.concat.call(null,(new cljs.core.List(null,new cljs.core.Symbol("same.ish","*comparator*","same.ish/*comparator*",(-1083476129),null),null,(1),null)),(new cljs.core.List(null,comparator,null,(1),null))))),null,(1),null)),body));
});

same.with_comparator.cljs$lang$maxFixedArity = (3);

/** @this {Function} */
same.with_comparator.cljs$lang$applyTo = (function (seq198){
var G__199 = cljs.core.first.call(null,seq198);
var seq198__$1 = cljs.core.next.call(null,seq198);
var G__200 = cljs.core.first.call(null,seq198__$1);
var seq198__$2 = cljs.core.next.call(null,seq198__$1);
var G__201 = cljs.core.first.call(null,seq198__$2);
var seq198__$3 = cljs.core.next.call(null,seq198__$2);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__199,G__200,G__201,seq198__$3);
});

return null;
})()
;
same.with_comparator.cljs$lang$macro = true;

