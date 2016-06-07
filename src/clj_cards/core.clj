(ns clj-cards.core
  (:gen-class))

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14))

(defn create-deck []
  (set
    (for [suit suits
          rank ranks]
      {:suit suit
       :rank rank})))

(defn create-hands [deck]
  (set
    (for [c1 deck
          c2 (disj deck c1)
          c3 (disj deck c1 c2)
          c4 (disj deck c1 c2 c3)]
      #{c1 c2 c3 c4})))

(defn sort-hand [hand]
  (map :rank
       (sort-by :rank hand)))

(defn flush? [hand]
  ; collects a set of which suits appear in the hand
  ; if they're all the same, return true
  (= 1(count (set (map :suit hand)))))

(defn straight? [hand]
  ; gets just the ranks from each hand
  (let [hand (sort-hand hand)
        ]
    ))
    ; if it's a straight but not a flush, return true
    ;(and straight (not flush))))

(defn four-of-a-kind? [hand]
  ; collects a set of which ranks appear in the hand
  ; if they're all the same, return true
  (= 1(count (set (map :rank hand)))))

(defn straight-flush? [hand]
  ; check if it's a flush
  (let [flush (flush? hand)
        ; check if it's a straight
        straight (straight? hand)]
    ; if both, return true
    (and flush straight)))


(defn three-of-a-kind? [hand]
  ; gets just the ranks from each card
  (let [hand (sort-hand hand)
        ; hash of cards & times they appear
        hand (frequencies hand)
        ; number of times first card appears
        firstrank (second (first hand))
        ; number of times second card appears
        secondrank (second (second hand))]
      ; if either one appears three times, return true
      (or (= 3 firstrank) (= 3 secondrank))))


(defn two-pair? [hand]
  ; gets just the ranks from each card
  (let [hand (sort-hand hand)
        ; hash of cards & times they appear
        hand (frequencies hand)
        ; get the number of times the first card appears
        firstrank (second (first hand))
        ; get the number of times the second card appears
        secondrank (second (second hand))]
    ; if both cards appear twice, return true
    (and (= 2 firstrank) (= 2 secondrank))))




(defn -main []
  (let [deck (create-deck)
        hands (create-hands deck)
        two-pair (filter two-pair? hands)]
    (println (count two-pair))))