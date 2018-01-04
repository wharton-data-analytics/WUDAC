;; open Assert
;; stop_on_failure ()

module type QUEUE = sig
  (* type of the data structure *)
  type 'a queue

  (* Make a new, empty queue *)
  val create : unit -> 'a queue

  (* Determine if the queue is empty *)
  val is_empty : 'a queue -> bool

  (* Add a value to the end of the queue *)
  val enq : 'a -> 'a queue -> unit

  (* Remove the first value (if any) and return it *)
  val deq : 'a queue -> 'a
	
  (* Queue length *)
  val length : 'a queue -> int
end


module Queue : QUEUE = struct
  (* INVARIANT:  q : 'a queue is a well-formed queue if 
     (1) q.head and q.tail are both None,  or
     (2) q.head is Some n1 and q.tail is Some n2 and
         - n2 is reachable from n1 following 'next' references
         - n2.next is None                                           *)
  type 'a qnode = {
    v : 'a;
    mutable next : 'a qnode option;
  }
  
  type 'a queue = {
    mutable head : 'a qnode option;
    mutable tail : 'a qnode option;
  }
  
  (*   ----- a cyclic queue ----- *)
  
 
  let cycle : int queue = 
		let qn = { v = 1; next = None } in
		let q  = { head = Some qn; tail = Some qn } in
		qn.next <- q.head;
		q

  let test () : bool =
    cycle == cycle
  ;; run_test "q == q" test

(*
  let test () : bool =
    cycle = cycle
  ;; run_test "cycle = cycle: infinite loop" test
*)
  
  (* ------ general queue operations ------- *)
  
  let create () = 
		{ head = None; tail = None; }
    
    
  let is_empty (q : 'a queue) : bool = 
    q.head = None (* && q.tail = None *)
    
  let enq (x : 'a) (q : 'a queue) : unit =
		let qno = Some { v = x; next = None } in
    begin match q.tail with
		| None -> 
		  q.head <- qno;
			q.tail <- qno
		| Some qn2 -> 
			q.tail  <- qno;
			qn2.next <- qno
		end
    
  let deq (q : 'a queue) : 'a =
    begin match q.head with
    | None -> failwith "deq: queue empty"
    | Some qn -> 
     	q.head <- qn.next;
        if qn.next = None then q.tail <- None;
      qn.v
    end
 
;; run_test "d1" (fun () ->
	let q = create () in
	enq 1 q;
	1 = deq q)
	
;; run_test "d2" (fun () ->
	let q = create () in
	enq 1 q;
	enq 2 q;
	ignore (deq q);
	2 = deq q) 

;; run_test "d3" (fun () ->
	let q = create () in
	enq 1 q;
	ignore (deq q);
	enq 2 q;
	2 = deq q) 

	let rec length (q: 'a queue) : int = 
		let rec loop (qno : 'a qnode option) (acc:int) : int = 
			begin match qno with
			| Some qn -> loop qn.next (1 + acc)
			| None -> acc
			end
		in
	
    loop q.head 0

;; run_test "length empty" (fun () ->
	  let q = create () in
		length q = 0)

;; run_test "length" (fun () ->
	  let q = create () in
		enq 1 q;
		enq 2 q;
		length q = 2)

end

;; open Queue

let test () : bool =
  is_empty (create ())
;; run_test "create empty" test

let test () : bool =
  let q1 = create () in
  let q2 = create () in
  not (q1 == q2)
;; run_test "create distinct queues" test

let test () : bool =
  let q = create () in
  enq 1 q;
  not (is_empty q)
;; run_test "create; enq nonempty" test


let test () : bool =
  let q : int queue = create () in
  enq 1 q;
  enq 2 q;
  1 = deq q
;; run_test "deq first" test

let test () : bool =
  let q : int queue = create () in
  enq 1 q;
  enq 2 q;
  let _ = deq q in
  2 = deq q
;; run_test "deq second" test

let test () : bool =
  let q : int queue = create () in
  enq 1 q;
  enq 2 q;
  let _ = deq q in
  enq 3 q;
  let _ = deq q in
  3 = deq q
;; run_test "enq enq deq enq deq deq" test

let test () : bool =
  let q : int queue = create () in
  enq 1 q;
  let _ = deq q in
  enq 2 q;
  2 = deq q
;; run_test "enq after deq" test

let test () : bool =
  let q : int queue = create () in
  let _ = deq q in
  false
;; run_failing_test "deq of empty should fail" test


let test () : bool =
  let q : int queue = create () in
  enq 2 q;
  let _ = deq q in
  is_empty q
;; run_test "enq then deq leaves empty" test 
