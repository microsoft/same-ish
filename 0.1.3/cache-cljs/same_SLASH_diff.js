goog.provide("same.diff");
/**
 * If `a` is an array, convert to a vector so it prints nicely.
 */
same.diff.un_array = (function same$diff$un_array(a){
if(same.platform.is_array_QMARK_.call(null,a)){
return cljs.core.vec.call(null,a);
} else {
return a;
}
});
same.diff.result_vec = (function same$diff$result_vec(var_args){
var args__10338__auto__ = [];
var len__10335__auto___53 = arguments.length;
var i__10336__auto___54 = (0);
while(true){
if((i__10336__auto___54 < len__10335__auto___53)){
args__10338__auto__.push((arguments[i__10336__auto___54]));

var G__55 = (i__10336__auto___54 + (1));
i__10336__auto___54 = G__55;
continue;
} else {
}
break;
}

var argseq__10339__auto__ = ((((2) < args__10338__auto__.length))?(new cljs.core.IndexedSeq(args__10338__auto__.slice((2)),(0),null)):null);
return same.diff.result_vec.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),argseq__10339__auto__);
});

same.diff.result_vec.cljs$core$IFn$_invoke$arity$variadic = (function (n,res,next){
if(cljs.core.empty_QMARK_.call(null,next)){
return res;
} else {
return cljs.core.into.call(null,cljs.core.into.call(null,(function (){var or__9218__auto__ = res;
if(cljs.core.truth_(or__9218__auto__)){
return or__9218__auto__;
} else {
return cljs.core.PersistentVector.EMPTY;
}
})(),cljs.core.take.call(null,(n - cljs.core.count.call(null,res)),cljs.core.repeat.call(null,null))),next);
}
});

same.diff.result_vec.cljs$lang$maxFixedArity = (2);

/** @this {Function} */
same.diff.result_vec.cljs$lang$applyTo = (function (seq50){
var G__51 = cljs.core.first.call(null,seq50);
var seq50__$1 = cljs.core.next.call(null,seq50);
var G__52 = cljs.core.first.call(null,seq50__$1);
var seq50__$2 = cljs.core.next.call(null,seq50__$1);
var self__10326__auto__ = this;
return self__10326__auto__.cljs$core$IFn$_invoke$arity$variadic(G__51,G__52,seq50__$2);
});


/**
 * Protocol for generating diffs of any type.
 * @interface
 */
same.diff.Diff = function(){};

/**
 * Returns the diff of two values, in the same format as `clojure.data/diff`,
 *  i.e. a vector of 3 elements: `[parts-only-in-this parts-only-in-that parts-in-both]`
 */
same.diff.diff = (function same$diff$diff(this$,that){
if((((!((this$ == null)))) && ((!((this$.same$diff$Diff$diff$arity$2 == null)))))){
return this$.same$diff$Diff$diff$arity$2(this$,that);
} else {
var x__9839__auto__ = (((this$ == null))?null:this$);
var m__9840__auto__ = (same.diff.diff[goog.typeOf(x__9839__auto__)]);
if((!((m__9840__auto__ == null)))){
return m__9840__auto__.call(null,this$,that);
} else {
var m__9837__auto__ = (same.diff.diff["_"]);
if((!((m__9837__auto__ == null)))){
return m__9837__auto__.call(null,this$,that);
} else {
throw cljs.core.missing_protocol.call(null,"Diff.diff",this$);
}
}
}
});

/**
 * Diff of sequential types, by comparing element-wise.
 */
same.diff.diff_seq = (function same$diff$diff_seq(this$,that){
var l = null;
var r = null;
var c = null;
var n = (0);
var left = this$;
var right = that;
while(true){
if(((cljs.core.empty_QMARK_.call(null,left)) || (cljs.core.empty_QMARK_.call(null,right)))){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.apply.call(null,same.diff.result_vec,n,l,left),cljs.core.apply.call(null,same.diff.result_vec,n,r,right),c], null);
} else {
var vec__65 = left;
var seq__66 = cljs.core.seq.call(null,vec__65);
var first__67 = cljs.core.first.call(null,seq__66);
var seq__66__$1 = cljs.core.next.call(null,seq__66);
var l0 = first__67;
var lm = seq__66__$1;
var vec__68 = right;
var seq__69 = cljs.core.seq.call(null,vec__68);
var first__70 = cljs.core.first.call(null,seq__69);
var seq__69__$1 = cljs.core.next.call(null,seq__69);
var r0 = first__70;
var rm = seq__69__$1;
if(cljs.core.truth_(same.ish.ish.call(null,l0,r0))){
var G__74 = l;
var G__75 = r;
var G__76 = same.diff.result_vec.call(null,n,c,r0);
var G__77 = (n + (1));
var G__78 = lm;
var G__79 = rm;
l = G__74;
r = G__75;
c = G__76;
n = G__77;
left = G__78;
right = G__79;
continue;
} else {
var vec__71 = same.diff.diff.call(null,l0,r0);
var dl = cljs.core.nth.call(null,vec__71,(0),null);
var dr = cljs.core.nth.call(null,vec__71,(1),null);
var dc = cljs.core.nth.call(null,vec__71,(2),null);
var G__80 = same.diff.result_vec.call(null,n,l,dl);
var G__81 = same.diff.result_vec.call(null,n,r,dr);
var G__82 = (((dc == null))?c:same.diff.result_vec.call(null,n,c,dc));
var G__83 = (n + (1));
var G__84 = lm;
var G__85 = rm;
l = G__80;
r = G__81;
c = G__82;
n = G__83;
left = G__84;
right = G__85;
continue;
}
}
break;
}
});
same.diff.update_common_keys = (function same$diff$update_common_keys(acc,lmap,rmap,keys){
return cljs.core.reduce.call(null,(function (m,k){
var vec__86 = same.diff.diff.call(null,cljs.core.get.call(null,lmap,k),cljs.core.get.call(null,rmap,k));
var dl = cljs.core.nth.call(null,vec__86,(0),null);
var dr = cljs.core.nth.call(null,vec__86,(1),null);
var dc = cljs.core.nth.call(null,vec__86,(2),null);
var G__89 = m;
var G__89__$1 = (((((!((dl == null)))) || ((!((dr == null))))))?cljs.core.assoc_in.call(null,G__89,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"l","l",(1395893423)),k], null),dl):G__89);
var G__89__$2 = (((((!((dl == null)))) || ((!((dr == null))))))?cljs.core.assoc_in.call(null,G__89__$1,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"r","r",(-471384190)),k], null),dr):G__89__$1);
if((!((dc == null)))){
return cljs.core.assoc_in.call(null,G__89__$2,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"c","c",(-1763192079)),k], null),dc);
} else {
return G__89__$2;
}
}),acc,keys);
});
same.diff.update_float_keys = (function same$diff$update_float_keys(acc,lmap,rmap,lkeys,rkeys){
var a = acc;
var lk = lkeys;
var rk = rkeys;
while(true){
if(((cljs.core.empty_QMARK_.call(null,lk)) && (cljs.core.empty_QMARK_.call(null,rk)))){
return a;
} else {
if(cljs.core.empty_QMARK_.call(null,lk)){
return cljs.core.reduce.call(null,((function (a,lk,rk){
return (function (p1__7_SHARP_,p2__8_SHARP_){
return cljs.core.assoc_in.call(null,p1__7_SHARP_,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"r","r",(-471384190)),p2__8_SHARP_], null),cljs.core.get.call(null,rmap,p2__8_SHARP_));
});})(a,lk,rk))
,a,rk);
} else {
if(cljs.core.empty_QMARK_.call(null,rk)){
return cljs.core.reduce.call(null,((function (a,lk,rk){
return (function (p1__9_SHARP_,p2__10_SHARP_){
return cljs.core.assoc_in.call(null,p1__9_SHARP_,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"l","l",(1395893423)),p2__10_SHARP_], null),cljs.core.get.call(null,lmap,p2__10_SHARP_));
});})(a,lk,rk))
,a,lk);
} else {
var vec__90 = lk;
var seq__91 = cljs.core.seq.call(null,vec__90);
var first__92 = cljs.core.first.call(null,seq__91);
var seq__91__$1 = cljs.core.next.call(null,seq__91);
var lk0 = first__92;
var lkr = seq__91__$1;
var vec__93 = rk;
var seq__94 = cljs.core.seq.call(null,vec__93);
var first__95 = cljs.core.first.call(null,seq__94);
var seq__94__$1 = cljs.core.next.call(null,seq__94);
var rk0 = first__95;
var rkr = seq__94__$1;
var lv = cljs.core.get.call(null,lmap,lk0);
var rv = cljs.core.get.call(null,rmap,rk0);
if(cljs.core.truth_(same.ish.ish.call(null,lk0,rk0))){
var vec__96 = same.diff.diff.call(null,lv,rv);
var dl = cljs.core.nth.call(null,vec__96,(0),null);
var dr = cljs.core.nth.call(null,vec__96,(1),null);
var dc = cljs.core.nth.call(null,vec__96,(2),null);
var acc__$1 = (function (){var G__99 = a;
var G__99__$1 = (((((!((dl == null)))) || ((!((dr == null))))))?cljs.core.assoc_in.call(null,G__99,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"l","l",(1395893423)),lk0], null),dl):G__99);
var G__99__$2 = (((((!((dl == null)))) || ((!((dr == null))))))?cljs.core.assoc_in.call(null,G__99__$1,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"r","r",(-471384190)),rk0], null),dr):G__99__$1);
if((!((dc == null)))){
return cljs.core.assoc_in.call(null,G__99__$2,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"c","c",(-1763192079)),rk0], null),dc);
} else {
return G__99__$2;
}
})();
var G__100 = acc__$1;
var G__101 = lkr;
var G__102 = rkr;
a = G__100;
lk = G__101;
rk = G__102;
continue;
} else {
if((lk0 < rk0)){
var G__103 = cljs.core.assoc_in.call(null,a,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"l","l",(1395893423)),lk0], null),lv);
var G__104 = lkr;
var G__105 = rk;
a = G__103;
lk = G__104;
rk = G__105;
continue;
} else {
var G__106 = cljs.core.assoc_in.call(null,a,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"r","r",(-471384190)),rk0], null),rv);
var G__107 = lk;
var G__108 = rkr;
a = G__106;
lk = G__107;
rk = G__108;
continue;

}
}

}
}
}
break;
}
});
goog.object.set(same.diff.Diff,"null",true);

goog.object.set(same.diff.diff,"null",(function (this$,that){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,that,null], null);
}));

goog.object.set(same.diff.Diff,"number",true);

goog.object.set(same.diff.diff,"number",(function (this$,that){
if(cljs.core.truth_(same.ish.ish.call(null,this$,that))){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,null,that], null);
} else {
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [this$,that,null], null);
}
}));

goog.object.set(same.diff.Diff,"boolean",true);

goog.object.set(same.diff.diff,"boolean",(function (this$,that){
if(cljs.core.truth_(same.ish.ish.call(null,this$,that))){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,null,that], null);
} else {
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [this$,that,null], null);
}
}));

goog.object.set(same.diff.Diff,"string",true);

goog.object.set(same.diff.diff,"string",(function (this$,that){
if(cljs.core.truth_(same.ish.ish.call(null,this$,that))){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,null,that], null);
} else {
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [this$,that,null], null);
}
}));

goog.object.set(same.diff.Diff,"array",true);

goog.object.set(same.diff.diff,"array",(function (this$,that){
if(same.platform.is_array_QMARK_.call(null,that)){
return same.diff.diff_seq.call(null,this$,that);
} else {
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [same.diff.un_array.call(null,this$),that,null], null);
}
}));

goog.object.set(same.diff.Diff,"object",true);

goog.object.set(same.diff.diff,"object",(function (this$,that){
if(cljs.core.truth_(same.ish.ish.call(null,this$,that))){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,null,same.diff.un_array.call(null,that)], null);
} else {
if(((cljs.core.sequential_QMARK_.call(null,this$)) && (cljs.core.sequential_QMARK_.call(null,that)))){
return same.diff.diff_seq.call(null,this$,that);
} else {
if(((cljs.core.map_QMARK_.call(null,this$)) && (cljs.core.map_QMARK_.call(null,that)))){
var vec__109 = same.ish.split_floats.call(null,cljs.core.keys.call(null,this$));
var this_floats = cljs.core.nth.call(null,vec__109,(0),null);
var this_rest = cljs.core.nth.call(null,vec__109,(1),null);
var vec__112 = same.ish.split_floats.call(null,cljs.core.keys.call(null,that));
var that_floats = cljs.core.nth.call(null,vec__112,(0),null);
var that_rest = cljs.core.nth.call(null,vec__112,(1),null);
var extract = cljs.core.juxt.call(null,new cljs.core.Keyword(null,"l","l",(1395893423)),new cljs.core.Keyword(null,"r","r",(-471384190)),new cljs.core.Keyword(null,"c","c",(-1763192079)));
return cljs.core.mapv.call(null,cljs.core.not_empty,extract.call(null,same.diff.update_float_keys.call(null,same.diff.update_common_keys.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"l","l",(1395893423)),cljs.core.select_keys.call(null,this$,clojure.set.difference.call(null,this_rest,that_rest)),new cljs.core.Keyword(null,"r","r",(-471384190)),cljs.core.select_keys.call(null,that,clojure.set.difference.call(null,that_rest,this_rest)),new cljs.core.Keyword(null,"c","c",(-1763192079)),cljs.core.PersistentArrayMap.EMPTY], null),this$,that,clojure.set.intersection.call(null,this_rest,that_rest)),this$,that,cljs.core.sort.call(null,this_floats),cljs.core.sort.call(null,that_floats))));
} else {
if(((cljs.core.set_QMARK_.call(null,this$)) && (cljs.core.set_QMARK_.call(null,that)))){
var vec__115 = same.ish.split_floats.call(null,this$);
var this_floats = cljs.core.nth.call(null,vec__115,(0),null);
var this_rest = cljs.core.nth.call(null,vec__115,(1),null);
var vec__118 = same.ish.split_floats.call(null,that);
var that_floats = cljs.core.nth.call(null,vec__118,(0),null);
var that_rest = cljs.core.nth.call(null,vec__118,(1),null);
var l = clojure.set.difference.call(null,this_rest,that_rest);
var r = clojure.set.difference.call(null,that_rest,this_rest);
var c = clojure.set.intersection.call(null,this_rest,that_rest);
var left = cljs.core.sort.call(null,this_floats);
var right = cljs.core.sort.call(null,that_floats);
while(true){
if(((cljs.core.empty_QMARK_.call(null,left)) || (cljs.core.empty_QMARK_.call(null,right)))){
return cljs.core.mapv.call(null,cljs.core.not_empty,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.into.call(null,l,left),cljs.core.into.call(null,r,right),c], null));
} else {
var vec__127 = left;
var seq__128 = cljs.core.seq.call(null,vec__127);
var first__129 = cljs.core.first.call(null,seq__128);
var seq__128__$1 = cljs.core.next.call(null,seq__128);
var vl = first__129;
var rl = seq__128__$1;
var vec__130 = right;
var seq__131 = cljs.core.seq.call(null,vec__130);
var first__132 = cljs.core.first.call(null,seq__131);
var seq__131__$1 = cljs.core.next.call(null,seq__131);
var vr = first__132;
var rr = seq__131__$1;
if(cljs.core.truth_(same.ish.ish.call(null,vl,vr))){
var G__133 = l;
var G__134 = r;
var G__135 = cljs.core.conj.call(null,c,vr);
var G__136 = rl;
var G__137 = rr;
l = G__133;
r = G__134;
c = G__135;
left = G__136;
right = G__137;
continue;
} else {
if((vl < vr)){
var G__138 = cljs.core.conj.call(null,l,vl);
var G__139 = r;
var G__140 = c;
var G__141 = rl;
var G__142 = right;
l = G__138;
r = G__139;
c = G__140;
left = G__141;
right = G__142;
continue;
} else {
var G__143 = l;
var G__144 = cljs.core.conj.call(null,r,vr);
var G__145 = c;
var G__146 = left;
var G__147 = rr;
l = G__143;
r = G__144;
c = G__145;
left = G__146;
right = G__147;
continue;

}
}
}
break;
}
} else {
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [this$,same.diff.un_array.call(null,that),null], null);

}
}
}
}
}));
