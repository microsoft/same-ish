goog.provide("same");
same.ish_QMARK_ = same.core.ish_QMARK_;
same.zeroish_QMARK_ = same.core.zeroish_QMARK_;
same.not_zeroish_QMARK_ = same.core.not_zeroish_QMARK_;
same.set_comparator_BANG_ = same.core.set_comparator_BANG_;
var ret__10368__auto___89 = (function (){
same.with_comparator = (function same$with_comparator(var_args){
var args__10338__auto__ = [];
var len__10335__auto___90 = arguments.length;
var i__10336__auto___91 = (0);
while(true){
if((i__10336__auto___91 < len__10335__auto___90)){
args__10338__auto__.push((arguments[i__10336__auto___91]));

var G__92 = (i__10336__auto___91 + (1));
i__10336__auto___91 = G__92;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((2) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((2)),(0),null)):null);
return same.with_comparator.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),argseq__10339__auto__);
});

same.with_comparator.cljs$core$IFn$_invoke$arity$variadic = (function (_AMPERSAND_form,_AMPERSAND_env,args){
return cljs.core.sequence.call(null,cljs.core.concat.call(null,(new cljs.core.List(null,new cljs.core.Symbol("same.core","with-comparator","same.core/with-comparator",(909646986),null),null,(1),null)),args));
});

same.with_comparator.cljs$lang$maxFixedArity = (2);

/** @this {Function} */
same.with_comparator.cljs$lang$applyTo = (function (seq86){
var G__87 = cljs.core.first.call(null,seq86);
var seq86__$1 = cljs.core.next.call(null,seq86);
var G__88 = cljs.core.first.call(null,seq86__$1);
var seq86__$2 = cljs.core.next.call(null,seq86__$1);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__87,G__88,seq86__$2);
});

return null;
})()
;
same.with_comparator.cljs$lang$macro = true;

