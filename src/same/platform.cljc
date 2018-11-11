;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.platform
  "Platform-specific code, to try to minimise reader conditionals in the rest of the codebase.")

(def max-value
  #?(:clj (Double/MAX_VALUE)
     :cljs (.-MAX_VALUE js/Number)))

(defn is-array?
  "Return true if `a` is an array."
  [a]
  #?(:clj (and a (.isArray ^Class (type a)))
     :cljs (array? a)))

(defn nan?
  "Return true if `f` is NaN (Not-a-Number)"
  [f]
  #?(:clj (Double/isNaN (double f))
     :cljs (js/isNaN f)))

(defn sign
  "Return the sign of `f` (+1 if positive, -1 if negative, 0 if zero or NaN if NaN)."
  [f]
  #?(:clj (Math/signum (double f))
     :cljs (cond
             (< f 0) -1
             (> f 0)  1
             (nan? f) f
             :else    0)))

(defn is-infinite?
  "Return true if `f` is infinite."
  [f]
  #?(:clj (Double/isInfinite (double f))
     :cljs (infinite? f)))

(defn to-float
  "Round `f` to a single precision (32-bit) float."
  [f]
  #?(:clj (float f)
     :cljs (let [arr (js/Float32Array. 1)]
             (aset arr 0 f)
             (aget arr 0))))

#?(:cljs
   (defn- ulp*
     [f]
     (let [f         (double (Math/abs (double f)))
           epsilon   (Math/pow 2.0 -52)
           max-value (* (- 2.0 epsilon) (Math/pow 2.0 1023))
           max-ulp   (Math/pow 2.0 971)]
       (cond
         (zero? f)        0.0
         (nan? f)         f
         (is-infinite? f) f
         (= max-value f)  max-ulp
         :else
         (let [buf (js/ArrayBuffer. 8)
               dv  (js/DataView. buf)
               _   (.setFloat64 dv 0 f)
               hi  (.getUint32 dv 0)
               lo  (.getUint32 dv 4)
               _   (.setUint32 dv 4 (inc lo))
               _   (when (= lo 0xffffffff)
                     (.setUint32 dv 0 (inc hi)))]
           (- (.getFloat64 dv 0) f))))))

(defn ulp
  "Units in the Last Place (ULP) of `f` (difference between f and the next largest representable number)."
  [f]
  #?(:clj (if (instance? Float f)
            (Math/ulp (float f))
            (Math/ulp (double f)))
     :cljs (ulp* f)))

(defn bit-diff-double
  "Difference between two doubles in ULPs (i.e. number of representable numbers between them + 1)."
  [f1 f2]
  #?(:clj (Math/abs ^long (- (Double/doubleToLongBits f1)
                             (Double/doubleToLongBits f2)))
     :cljs (let [buf (js/ArrayBuffer. 16)
                 dv  (js/DataView. buf)]
             (.setFloat64 dv 0 (double f1))
             (.setFloat64 dv 8 (double f2))
             (Math/abs
              (+ (* (- (.getUint32 dv 0) (.getUint32 dv 8)) 0x100000000)
                 (- (.getUint32 dv 4) (.getUint32 dv 12)))))))

(defn bit-diff-float
  "Difference between two floats in ULPs (i.e. number of representable numbers between them + 1)."
  [f1 f2]
  #?(:clj (Math/abs ^long (- (Float/floatToIntBits f1)
                             (Float/floatToIntBits f2)))
     :cljs (let [buf (js/ArrayBuffer. 8)
                 dv  (js/DataView. buf)]
             (.setFloat32 dv 0 (float f1))
             (.setFloat32 dv 4 (float f2))
             (Math/abs (- (.getUint32 dv 0) (.getUint32 dv 4))))))
