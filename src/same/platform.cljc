;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.platform)

(defn is-array?
  [a]
  #?(:clj (and a (.isArray ^Class (type a)))
     :cljs (array? a)))

(defn sign
  [f]
  #?(:clj (Math/signum (double f))
     :cljs (cond
             (< f 0) -1
             (> f 0)  1
             :else    0)))

(defn is-infinite?
  [f]
  #?(:clj (Double/isInfinite (double f))
     :cljs (infinite? f)))

(defn- ulp*
  [f]
  (let [f         (double (Math/abs (double f)))
        epsilon   (Math/pow 2.0 -52)
        max-value (* (- 2.0 epsilon) (Math/pow 2.0 1023))]
    (cond
      (not= f f)       f ;; NaN
      (is-infinite? f) f
      #_(= max-value f) ;; TODO
      :else            (/ f 1e15))))

(defn ulp
  [f]
  #?(:clj (if (instance? Float f)
            (Math/ulp (float f))
            (Math/ulp (double f)))
     :cljs (ulp* f)))

(defn bit-diff-double
  [f1 f2]
  #?(:clj (Math/abs ^long (- (Double/doubleToLongBits f1)
                             (Double/doubleToLongBits f2)))
     :cljs (let [[f1 f2] [(max f1 f2) (min f1 f2)]
                 buf (js/ArrayBuffer. 16)
                 dv  (js/DataView. buf)]
             (.setFloat64 dv 0 (double f1))
             (.setFloat64 dv 8 (double f2))
             (+ (bit-shift-left (- (.getUint32 dv 0) (.getUint32 dv 8)) 32)
                (- (.getUint32 dv 4) (.getUint32 dv 12))))))

(defn bit-diff-float
  [f1 f2]
  #?(:clj (Math/abs ^long (- (Float/floatToIntBits f1)
                             (Float/floatToIntBits f2)))
     :cljs (let [buf (js/ArrayBuffer. 8)
                 dv  (js/DataView. buf)]
             (.setFloat32 dv 0 (float f1))
             (.setFloat32 dv 4 (float f2))
             (Math/abs (- (.getUint32 dv 0) (.getUint32 dv 4))))))
