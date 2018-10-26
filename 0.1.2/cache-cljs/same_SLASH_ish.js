goog.provide("same.ish");
/**
 * The default comparator. Stored in a separate var to make it easier to reset after
 *   modifying with [[set-comparator!]]
 */
same.ish.default_comparator = same.compare.compare_ulp.call(null,(100),(2));
/**
 * The function for comparing individual floats/doubles.
 *   Can be overridden using [[with-comparator]] or [[set-comparator!]].
 */
same.ish._STAR_comparator_STAR_ = same.ish.default_comparator;
/**
 * Split a collection into a vector of floating point values (of type Float or Double),
 *   and a set of all other values.
 */
same.ish.split_floats = (function same$ish$split_floats(coll){
return cljs.core.reduce.call(null,(function (p__22,v){
var vec__23 = p__22;
var floats = cljs.core.nth.call(null,vec__23,(0),null);
var rest = cljs.core.nth.call(null,vec__23,(1),null);
if(cljs.core.float_QMARK_.call(null,v)){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.conj.call(null,floats,v),rest], null);
} else {
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [floats,cljs.core.conj.call(null,rest,v)], null);
}
}),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.PersistentVector.EMPTY,cljs.core.PersistentHashSet.EMPTY], null),coll);
});

/**
 * Protocol for approximately comparing any types (using [[*comparator*]] for floating point parts).
 * @interface
 */
same.ish.Approximate = function(){};

/**
 * Return true if the two arguments are approximately equal.
 */
same.ish.ish = (function same$ish$ish(this$,that){
if((!((this$ == null))) && (!((this$.same$ish$Approximate$ish$arity$2 == null)))){
return this$.same$ish$Approximate$ish$arity$2(this$,that);
} else {
var x__22826__auto__ = (((this$ == null))?null:this$);
var m__22827__auto__ = (same.ish.ish[goog.typeOf(x__22826__auto__)]);
if(!((m__22827__auto__ == null))){
return m__22827__auto__.call(null,this$,that);
} else {
var m__22827__auto____$1 = (same.ish.ish["_"]);
if(!((m__22827__auto____$1 == null))){
return m__22827__auto____$1.call(null,this$,that);
} else {
throw cljs.core.missing_protocol.call(null,"Approximate.ish",this$);
}
}
}
});

goog.object.set(same.ish.Approximate,"null",true);

goog.object.set(same.ish.ish,"null",(function (this$,that){
return (that == null);
}));

goog.object.set(same.ish.Approximate,"number",true);

goog.object.set(same.ish.ish,"number",(function (this$,that){
if(typeof that === 'number'){
return same.ish._STAR_comparator_STAR_.call(null,this$,that);
} else {
return cljs.core._EQ_.call(null,this$,that);
}
}));

goog.object.set(same.ish.Approximate,"boolean",true);

goog.object.set(same.ish.ish,"boolean",(function (this$,that){
return cljs.core._EQ_.call(null,this$,that);
}));

goog.object.set(same.ish.Approximate,"array",true);

goog.object.set(same.ish.ish,"array",(function (this$,that){
var and__22202__auto__ = same.platform.is_array_QMARK_.call(null,that);
if(cljs.core.truth_(and__22202__auto__)){
return (cljs.core._EQ_.call(null,cljs.core.count.call(null,this$),cljs.core.count.call(null,that))) && (cljs.core.every_QMARK_.call(null,cljs.core.identity,cljs.core.map.call(null,same.ish.ish,this$,that)));
} else {
return and__22202__auto__;
}
}));

goog.object.set(same.ish.Approximate,"string",true);

goog.object.set(same.ish.ish,"string",(function (this$,that){
return cljs.core._EQ_.call(null,this$,that);
}));

goog.object.set(same.ish.Approximate,"object",true);

goog.object.set(same.ish.ish,"object",(function (this$,that){
if(cljs.core._EQ_.call(null,this$,that)){
return true;
} else {
if((cljs.core.sequential_QMARK_.call(null,this$)) && (cljs.core.sequential_QMARK_.call(null,that))){
return (cljs.core._EQ_.call(null,cljs.core.count.call(null,this$),cljs.core.count.call(null,that))) && (cljs.core.every_QMARK_.call(null,cljs.core.partial.call(null,cljs.core.apply,same.ish.ish),cljs.core.map.call(null,cljs.core.vector,this$,that)));
} else {
if((cljs.core.map_QMARK_.call(null,this$)) && (cljs.core.map_QMARK_.call(null,that))){
var and__22202__auto__ = cljs.core._EQ_.call(null,cljs.core.count.call(null,this$),cljs.core.count.call(null,that));
if(and__22202__auto__){
var vec__32 = same.ish.split_floats.call(null,cljs.core.keys.call(null,this$));
var this_floats = cljs.core.nth.call(null,vec__32,(0),null);
var this_rest = cljs.core.nth.call(null,vec__32,(1),null);
var vec__35 = same.ish.split_floats.call(null,cljs.core.keys.call(null,that));
var that_floats = cljs.core.nth.call(null,vec__35,(0),null);
var that_rest = cljs.core.nth.call(null,vec__35,(1),null);
return (cljs.core._EQ_.call(null,this_rest,that_rest)) && (cljs.core.every_QMARK_.call(null,((function (vec__32,this_floats,this_rest,vec__35,that_floats,that_rest,and__22202__auto__){
return (function (p1__4_SHARP_){
return same.ish.ish.call(null,cljs.core.get.call(null,this$,p1__4_SHARP_),cljs.core.get.call(null,that,p1__4_SHARP_));
});})(vec__32,this_floats,this_rest,vec__35,that_floats,that_rest,and__22202__auto__))
,this_rest)) && (cljs.core.every_QMARK_.call(null,cljs.core.identity,cljs.core.map.call(null,((function (vec__32,this_floats,this_rest,vec__35,that_floats,that_rest,and__22202__auto__){
return (function (p1__5_SHARP_,p2__6_SHARP_){
var and__22202__auto____$1 = same.ish.ish.call(null,p1__5_SHARP_,p2__6_SHARP_);
if(cljs.core.truth_(and__22202__auto____$1)){
return same.ish.ish.call(null,cljs.core.get.call(null,this$,p1__5_SHARP_),cljs.core.get.call(null,that,p2__6_SHARP_));
} else {
return and__22202__auto____$1;
}
});})(vec__32,this_floats,this_rest,vec__35,that_floats,that_rest,and__22202__auto__))
,cljs.core.sort.call(null,this_floats),cljs.core.sort.call(null,that_floats))));
} else {
return and__22202__auto__;
}
} else {
if((cljs.core.set_QMARK_.call(null,this$)) && (cljs.core.set_QMARK_.call(null,that))){
var and__22202__auto__ = cljs.core._EQ_.call(null,cljs.core.count.call(null,this$),cljs.core.count.call(null,that));
if(and__22202__auto__){
var vec__44 = same.ish.split_floats.call(null,this$);
var this_floats = cljs.core.nth.call(null,vec__44,(0),null);
var this_rest = cljs.core.nth.call(null,vec__44,(1),null);
var vec__47 = same.ish.split_floats.call(null,that);
var that_floats = cljs.core.nth.call(null,vec__47,(0),null);
var that_rest = cljs.core.nth.call(null,vec__47,(1),null);
var and__22202__auto____$1 = cljs.core._EQ_.call(null,this_rest,that_rest);
if(and__22202__auto____$1){
return same.ish.ish.call(null,cljs.core.sort.call(null,this_floats),cljs.core.sort.call(null,that_floats));
} else {
return and__22202__auto____$1;
}
} else {
return and__22202__auto__;
}
} else {
return false;

}
}
}
}
}));
