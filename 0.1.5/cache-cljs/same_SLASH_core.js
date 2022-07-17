goog.provide("same.core");
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
same.core.ish_QMARK_ = (function same$core$ish_QMARK_(var_args){
var args__10338__auto__ = [];
var len__10335__auto___52 = arguments.length;
var i__10336__auto___53 = (0);
while(true){
if((i__10336__auto___53 < len__10335__auto___52)){
args__10338__auto__.push((arguments[i__10336__auto___53]));

var G__54 = (i__10336__auto___53 + (1));
i__10336__auto___53 = G__54;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((1) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((1)),(0),null)):null);
return same.core.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__10339__auto__);
});

same.core.ish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (expected,actuals){
if(cljs.core.truth_(cljs.core.not_empty.call(null,actuals))){
} else {
throw (new Error("Assert failed: (not-empty actuals)"));
}

return cljs.core.every_QMARK_.call(null,cljs.core.partial.call(null,same.ish.ish,expected),actuals);
});

same.core.ish_QMARK_.cljs$lang$maxFixedArity = (1);

/** @this {Function} */
same.core.ish_QMARK_.cljs$lang$applyTo = (function (seq50){
var G__51 = cljs.core.first.call(null,seq50);
var seq50__$1 = cljs.core.next.call(null,seq50);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__51,seq50__$1);
});

/**
 * Compare a numeric value to zero, returning true if close.
 *   ```klipse
 *   (zeroish? 0.0000000001
 *          :scale 1e6)
 *   ```
 */
same.core.zeroish_QMARK_ = (function same$core$zeroish_QMARK_(var_args){
var args__10338__auto__ = [];
var len__10335__auto___60 = arguments.length;
var i__10336__auto___61 = (0);
while(true){
if((i__10336__auto___61 < len__10335__auto___60)){
args__10338__auto__.push((arguments[i__10336__auto___61]));

var G__62 = (i__10336__auto___61 + (1));
i__10336__auto___61 = G__62;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((1) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((1)),(0),null)):null);
return same.core.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__10339__auto__);
});

same.core.zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__57){
var map__58 = p__57;
var map__58__$1 = (((((!((map__58 == null))))?(((((map__58.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__58.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__58):map__58);
var scale = cljs.core.get.call(null,map__58__$1,new cljs.core.Keyword(null,"scale","scale",(-230427353)),(1000));
return same.compare.near_zero.call(null,val,scale);
});

same.core.zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

/** @this {Function} */
same.core.zeroish_QMARK_.cljs$lang$applyTo = (function (seq55){
var G__56 = cljs.core.first.call(null,seq55);
var seq55__$1 = cljs.core.next.call(null,seq55);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__56,seq55__$1);
});

/**
 * Compare a numeric value to zero, returning true if not close. Equivalent to `(not (zeroish? ...))`.
 *   ```klipse
 *   (not-zeroish? 3 :scale 1e6)
 *   ```
 */
same.core.not_zeroish_QMARK_ = (function same$core$not_zeroish_QMARK_(var_args){
var args__10338__auto__ = [];
var len__10335__auto___68 = arguments.length;
var i__10336__auto___69 = (0);
while(true){
if((i__10336__auto___69 < len__10335__auto___68)){
args__10338__auto__.push((arguments[i__10336__auto___69]));

var G__70 = (i__10336__auto___69 + (1));
i__10336__auto___69 = G__70;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((1) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((1)),(0),null)):null);
return same.core.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__10339__auto__);
});

same.core.not_zeroish_QMARK_.cljs$core$IFn$_invoke$arity$variadic = (function (val,p__65){
var map__66 = p__65;
var map__66__$1 = (((((!((map__66 == null))))?(((((map__66.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__66.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__66):map__66);
var scale = cljs.core.get.call(null,map__66__$1,new cljs.core.Keyword(null,"scale","scale",(-230427353)),(1000));
return (!(same.compare.near_zero.call(null,val,scale)));
});

same.core.not_zeroish_QMARK_.cljs$lang$maxFixedArity = (1);

/** @this {Function} */
same.core.not_zeroish_QMARK_.cljs$lang$applyTo = (function (seq63){
var G__64 = cljs.core.first.call(null,seq63);
var seq63__$1 = cljs.core.next.call(null,seq63);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__64,seq63__$1);
});

/**
 * Set the default comparator.
 *   ```klipse
 *   (set-comparator! (compare-ulp 2.0 100))
 *   (ish? 0.1 (-> 2 Math/sqrt (Math/pow 2) (- 1.9)))
 *   ```
 */
same.core.set_comparator_BANG_ = (function same$core$set_comparator_BANG_(comparator){
same.ish._STAR_comparator_STAR_ = comparator;

return null;
});
var ret__10368__auto___75 = (function (){
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
same.core.with_comparator = (function same$core$with_comparator(var_args){
var args__10338__auto__ = [];
var len__10335__auto___76 = arguments.length;
var i__10336__auto___77 = (0);
while(true){
if((i__10336__auto___77 < len__10335__auto___76)){
args__10338__auto__.push((arguments[i__10336__auto___77]));

var G__78 = (i__10336__auto___77 + (1));
i__10336__auto___77 = G__78;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((3) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((3)),(0),null)):null);
return same.core.with_comparator.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),argseq__10339__auto__);
});

same.core.with_comparator.cljs$core$IFn$_invoke$arity$variadic = (function (_AMPERSAND_form,_AMPERSAND_env,comparator,body){
return cljs.core.sequence.call(null,cljs.core.concat.call(null,(new cljs.core.List(null,new cljs.core.Symbol("cljs.core","binding","cljs.core/binding",(2050379843),null),null,(1),null)),(new cljs.core.List(null,cljs.core.vec.call(null,cljs.core.sequence.call(null,cljs.core.concat.call(null,(new cljs.core.List(null,new cljs.core.Symbol("same.ish","*comparator*","same.ish/*comparator*",(-1083476129),null),null,(1),null)),(new cljs.core.List(null,comparator,null,(1),null))))),null,(1),null)),body));
});

same.core.with_comparator.cljs$lang$maxFixedArity = (3);

/** @this {Function} */
same.core.with_comparator.cljs$lang$applyTo = (function (seq71){
var G__72 = cljs.core.first.call(null,seq71);
var seq71__$1 = cljs.core.next.call(null,seq71);
var G__73 = cljs.core.first.call(null,seq71__$1);
var seq71__$2 = cljs.core.next.call(null,seq71__$1);
var G__74 = cljs.core.first.call(null,seq71__$2);
var seq71__$3 = cljs.core.next.call(null,seq71__$2);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__72,G__73,G__74,seq71__$3);
});

return null;
})()
;
same.core.with_comparator.cljs$lang$macro = true;

