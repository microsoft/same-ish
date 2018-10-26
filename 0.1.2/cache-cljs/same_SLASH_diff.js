goog.provide("same.diff");
/**
 * If `a` is an array, convert to a vector so it prints nicely.
 */
same.diff.un_array = (function same$diff$un_array(a){
if(cljs.core.truth_(same.platform.is_array_QMARK_.call(null,a))){
return cljs.core.vec.call(null,a);
} else {
return a;
}
});
same.diff.result_vec = (function same$diff$result_vec(var_args){
var args__23329__auto__ = [];
var len__23326__auto___53 = arguments.length;
var i__23327__auto___54 = (0);
while(true){
if((i__23327__auto___54 < len__23326__auto___53)){
args__23329__auto__.push((arguments[i__23327__auto___54]));

var G__55 = (i__23327__auto___54 + (1));
i__23327__auto___54 = G__55;
continue;
} else {
}
break;
}

var argseq__23330__auto__ = ((((2) < args__23329__auto__.length))?(new cljs.core.IndexedSeq(args__23329__auto__.slice((2)),(0),null)):null);
return same.diff.result_vec.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),argseq__23330__auto__);
});

same.diff.result_vec.cljs$core$IFn$_invoke$arity$variadic = (function (n,res,next){
if(cljs.core.empty_QMARK_.call(null,next)){
return res;
} else {
return cljs.core.into.call(null,cljs.core.into.call(null,(function (){var or__22217__auto__ = res;
if(cljs.core.truth_(or__22217__auto__)){
return or__22217__auto__;
} else {
return cljs.core.PersistentVector.EMPTY;
}
})(),cljs.core.take.call(null,(n - cljs.core.count.call(null,res)),cljs.core.repeat.call(null,null))),next);
}
});

same.diff.result_vec.cljs$lang$maxFixedArity = (2);

same.diff.result_vec.cljs$lang$applyTo = (function (seq50){
var G__51 = cljs.core.first.call(null,seq50);
var seq50__$1 = cljs.core.next.call(null,seq50);
var G__52 = cljs.core.first.call(null,seq50__$1);
var seq50__$2 = cljs.core.next.call(null,seq50__$1);
return same.diff.result_vec.cljs$core$IFn$_invoke$arity$variadic(G__51,G__52,seq50__$2);
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
if((!((this$ == null))) && (!((this$.same$diff$Diff$diff$arity$2 == null)))){
return this$.same$diff$Diff$diff$arity$2(this$,that);
} else {
var x__22826__auto__ = (((this$ == null))?null:this$);
var m__22827__auto__ = (same.diff.diff[goog.typeOf(x__22826__auto__)]);
if(!((m__22827__auto__ == null))){
return m__22827__auto__.call(null,this$,that);
} else {
var m__22827__auto____$1 = (same.diff.diff["_"]);
if(!((m__22827__auto____$1 == null))){
return m__22827__auto____$1.call(null,this$,that);
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
if((cljs.core.empty_QMARK_.call(null,left)) || (cljs.core.empty_QMARK_.call(null,right))){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.apply.call(null,same.diff.result_vec,n,l,left),cljs.core.apply.call(null,same.diff.result_vec,n,r,right),c], null);
} else {
var vec__56 = left;
var seq__57 = cljs.core.seq.call(null,vec__56);
var first__58 = cljs.core.first.call(null,seq__57);
var seq__57__$1 = cljs.core.next.call(null,seq__57);
var l0 = first__58;
var lm = seq__57__$1;
var vec__59 = right;
var seq__60 = cljs.core.seq.call(null,vec__59);
var first__61 = cljs.core.first.call(null,seq__60);
var seq__60__$1 = cljs.core.next.call(null,seq__60);
var r0 = first__61;
var rm = seq__60__$1;
if(cljs.core.truth_(same.ish.ish.call(null,l0,r0))){
var G__65 = l;
var G__66 = r;
var G__67 = same.diff.result_vec.call(null,n,c,r0);
var G__68 = (n + (1));
var G__69 = lm;
var G__70 = rm;
l = G__65;
r = G__66;
c = G__67;
n = G__68;
left = G__69;
right = G__70;
continue;
} else {
var vec__62 = same.diff.diff.call(null,l0,r0);
var dl = cljs.core.nth.call(null,vec__62,(0),null);
var dr = cljs.core.nth.call(null,vec__62,(1),null);
var dc = cljs.core.nth.call(null,vec__62,(2),null);
var G__71 = same.diff.result_vec.call(null,n,l,dl);
var G__72 = same.diff.result_vec.call(null,n,r,dr);
var G__73 = (((dc == null))?c:same.diff.result_vec.call(null,n,c,dc));
var G__74 = (n + (1));
var G__75 = lm;
var G__76 = rm;
l = G__71;
r = G__72;
c = G__73;
n = G__74;
left = G__75;
right = G__76;
continue;
}
}
break;
}
});
same.diff.update_common_keys = (function same$diff$update_common_keys(acc,lmap,rmap,keys){
return cljs.core.reduce.call(null,(function (m,k){
var vec__77 = same.diff.diff.call(null,cljs.core.get.call(null,lmap,k),cljs.core.get.call(null,rmap,k));
var dl = cljs.core.nth.call(null,vec__77,(0),null);
var dr = cljs.core.nth.call(null,vec__77,(1),null);
var dc = cljs.core.nth.call(null,vec__77,(2),null);
var G__80 = m;
var G__80__$1 = (((!((dl == null))) || (!((dr == null))))?cljs.core.assoc_in.call(null,G__80,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"l","l",(1395893423)),k], null),dl):G__80);
var G__80__$2 = (((!((dl == null))) || (!((dr == null))))?cljs.core.assoc_in.call(null,G__80__$1,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"r","r",(-471384190)),k], null),dr):G__80__$1);
if(!((dc == null))){
return cljs.core.assoc_in.call(null,G__80__$2,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"c","c",(-1763192079)),k], null),dc);
} else {
return G__80__$2;
}
}),acc,keys);
});
same.diff.update_float_keys = (function same$diff$update_float_keys(acc,lmap,rmap,lkeys,rkeys){
var a = acc;
var lk = lkeys;
var rk = rkeys;
while(true){
if((cljs.core.empty_QMARK_.call(null,lk)) && (cljs.core.empty_QMARK_.call(null,rk))){
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
var vec__81 = lk;
var seq__82 = cljs.core.seq.call(null,vec__81);
var first__83 = cljs.core.first.call(null,seq__82);
var seq__82__$1 = cljs.core.next.call(null,seq__82);
var lk0 = first__83;
var lkr = seq__82__$1;
var vec__84 = rk;
var seq__85 = cljs.core.seq.call(null,vec__84);
var first__86 = cljs.core.first.call(null,seq__85);
var seq__85__$1 = cljs.core.next.call(null,seq__85);
var rk0 = first__86;
var rkr = seq__85__$1;
var lv = cljs.core.get.call(null,lmap,lk0);
var rv = cljs.core.get.call(null,rmap,rk0);
if(cljs.core.truth_(same.ish.ish.call(null,lk0,rk0))){
var vec__87 = same.diff.diff.call(null,lv,rv);
var dl = cljs.core.nth.call(null,vec__87,(0),null);
var dr = cljs.core.nth.call(null,vec__87,(1),null);
var dc = cljs.core.nth.call(null,vec__87,(2),null);
var acc__$1 = (function (){var G__90 = a;
var G__90__$1 = (((!((dl == null))) || (!((dr == null))))?cljs.core.assoc_in.call(null,G__90,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"l","l",(1395893423)),lk0], null),dl):G__90);
var G__90__$2 = (((!((dl == null))) || (!((dr == null))))?cljs.core.assoc_in.call(null,G__90__$1,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"r","r",(-471384190)),rk0], null),dr):G__90__$1);
if(!((dc == null))){
return cljs.core.assoc_in.call(null,G__90__$2,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"c","c",(-1763192079)),rk0], null),dc);
} else {
return G__90__$2;
}
})();
var G__91 = acc__$1;
var G__92 = lkr;
var G__93 = rkr;
a = G__91;
lk = G__92;
rk = G__93;
continue;
} else {
if((lk0 < rk0)){
var G__94 = cljs.core.assoc_in.call(null,a,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"l","l",(1395893423)),lk0], null),lv);
var G__95 = lkr;
var G__96 = rk;
a = G__94;
lk = G__95;
rk = G__96;
continue;
} else {
var G__97 = cljs.core.assoc_in.call(null,a,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"r","r",(-471384190)),rk0], null),rv);
var G__98 = lk;
var G__99 = rkr;
a = G__97;
lk = G__98;
rk = G__99;
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
if(cljs.core.truth_(same.platform.is_array_QMARK_.call(null,that))){
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
if((cljs.core.sequential_QMARK_.call(null,this$)) && (cljs.core.sequential_QMARK_.call(null,that))){
return same.diff.diff_seq.call(null,this$,that);
} else {
if((cljs.core.map_QMARK_.call(null,this$)) && (cljs.core.map_QMARK_.call(null,that))){
var vec__100 = same.ish.split_floats.call(null,cljs.core.keys.call(null,this$));
var this_floats = cljs.core.nth.call(null,vec__100,(0),null);
var this_rest = cljs.core.nth.call(null,vec__100,(1),null);
var vec__103 = same.ish.split_floats.call(null,cljs.core.keys.call(null,that));
var that_floats = cljs.core.nth.call(null,vec__103,(0),null);
var that_rest = cljs.core.nth.call(null,vec__103,(1),null);
var extract = cljs.core.juxt.call(null,new cljs.core.Keyword(null,"l","l",(1395893423)),new cljs.core.Keyword(null,"r","r",(-471384190)),new cljs.core.Keyword(null,"c","c",(-1763192079)));
return cljs.core.mapv.call(null,cljs.core.not_empty,extract.call(null,same.diff.update_float_keys.call(null,same.diff.update_common_keys.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"l","l",(1395893423)),cljs.core.select_keys.call(null,this$,clojure.set.difference.call(null,this_rest,that_rest)),new cljs.core.Keyword(null,"r","r",(-471384190)),cljs.core.select_keys.call(null,that,clojure.set.difference.call(null,that_rest,this_rest)),new cljs.core.Keyword(null,"c","c",(-1763192079)),cljs.core.PersistentArrayMap.EMPTY], null),this$,that,clojure.set.intersection.call(null,this_rest,that_rest)),this$,that,cljs.core.sort.call(null,this_floats),cljs.core.sort.call(null,that_floats))));
} else {
if((cljs.core.set_QMARK_.call(null,this$)) && (cljs.core.set_QMARK_.call(null,that))){
var vec__106 = same.ish.split_floats.call(null,this$);
var this_floats = cljs.core.nth.call(null,vec__106,(0),null);
var this_rest = cljs.core.nth.call(null,vec__106,(1),null);
var vec__109 = same.ish.split_floats.call(null,that);
var that_floats = cljs.core.nth.call(null,vec__109,(0),null);
var that_rest = cljs.core.nth.call(null,vec__109,(1),null);
var l = clojure.set.difference.call(null,this_rest,that_rest);
var r = clojure.set.difference.call(null,that_rest,this_rest);
var c = clojure.set.intersection.call(null,this_rest,that_rest);
var left = cljs.core.sort.call(null,this_floats);
var right = cljs.core.sort.call(null,that_floats);
while(true){
if((cljs.core.empty_QMARK_.call(null,left)) || (cljs.core.empty_QMARK_.call(null,right))){
return cljs.core.mapv.call(null,cljs.core.not_empty,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.into.call(null,l,left),cljs.core.into.call(null,r,right),c], null));
} else {
var vec__112 = left;
var seq__113 = cljs.core.seq.call(null,vec__112);
var first__114 = cljs.core.first.call(null,seq__113);
var seq__113__$1 = cljs.core.next.call(null,seq__113);
var vl = first__114;
var rl = seq__113__$1;
var vec__115 = right;
var seq__116 = cljs.core.seq.call(null,vec__115);
var first__117 = cljs.core.first.call(null,seq__116);
var seq__116__$1 = cljs.core.next.call(null,seq__116);
var vr = first__117;
var rr = seq__116__$1;
if(cljs.core.truth_(same.ish.ish.call(null,vl,vr))){
var G__118 = l;
var G__119 = r;
var G__120 = cljs.core.conj.call(null,c,vr);
var G__121 = rl;
var G__122 = rr;
l = G__118;
r = G__119;
c = G__120;
left = G__121;
right = G__122;
continue;
} else {
if((vl < vr)){
var G__123 = cljs.core.conj.call(null,l,vl);
var G__124 = r;
var G__125 = c;
var G__126 = rl;
var G__127 = right;
l = G__123;
r = G__124;
c = G__125;
left = G__126;
right = G__127;
continue;
} else {
var G__128 = l;
var G__129 = cljs.core.conj.call(null,r,vr);
var G__130 = c;
var G__131 = left;
var G__132 = rr;
l = G__128;
r = G__129;
c = G__130;
left = G__131;
right = G__132;
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
