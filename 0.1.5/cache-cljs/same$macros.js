goog.provide("same$macros");
same$macros.ish_QMARK_ = same.core.ish_QMARK_;
same$macros.zeroish_QMARK_ = same.core.zeroish_QMARK_;
same$macros.not_zeroish_QMARK_ = same.core.not_zeroish_QMARK_;
same$macros.set_comparator_BANG_ = same.core.set_comparator_BANG_;
var ret__10368__auto___82 = (function (){
same$macros.with_comparator = (function same$macros$with_comparator(var_args){
var args__10338__auto__ = [];
var len__10335__auto___83 = arguments.length;
var i__10336__auto___84 = (0);
while(true){
if((i__10336__auto___84 < len__10335__auto___83)){
args__10338__auto__.push((arguments[i__10336__auto___84]));

var G__85 = (i__10336__auto___84 + (1));
i__10336__auto___84 = G__85;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((2) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((2)),(0),null)):null);
return same$macros.with_comparator.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),argseq__10339__auto__);
});

same$macros.with_comparator.cljs$core$IFn$_invoke$arity$variadic = (function (_AMPERSAND_form,_AMPERSAND_env,args){
return cljs.core.sequence.call(null,cljs.core.concat.call(null,(new cljs.core.List(null,new cljs.core.Symbol("same.core","with-comparator","same.core/with-comparator",(909646986),null),null,(1),null)),args));
});

same$macros.with_comparator.cljs$lang$maxFixedArity = (2);

/** @this {Function} */
same$macros.with_comparator.cljs$lang$applyTo = (function (seq79){
var G__80 = cljs.core.first.call(null,seq79);
var seq79__$1 = cljs.core.next.call(null,seq79);
var G__81 = cljs.core.first.call(null,seq79__$1);
var seq79__$2 = cljs.core.next.call(null,seq79__$1);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__80,G__81,seq79__$2);
});

return null;
})()
;
same$macros.with_comparator.cljs$lang$macro = true;

