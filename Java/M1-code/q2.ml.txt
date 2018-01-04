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
	
	val to_list : 'a queue -> 'a list
	val print : 'a queue -> ('a -> string) -> unit
end


module Queue : QUEUE = struct
  (* Queue INVARIANT:
	  Either (1) q.head and q.tail are both None
		    or (2) q.head is Some n1 and q.tail is Some n2
				    and n2 is reachable from n1 following .next 
						and n2.next is None
	*)
	type 'a qnode = { v : 'a
		              ; mutable next : 'a qnode option } 
	
	type 'a queue = { mutable head : 'a qnode option
		              ; mutable tail : 'a qnode option }

	
		
			
				
					
				
								
(*   ----- an ill-formed cyclic queue value ----- *)
  
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
		{ head = None; tail = None }
    
  let is_empty (q : 'a queue) : bool = 
    q.head = None
    
  let enq (x : 'a) (q : 'a queue) : unit =
		let newnode = {v = x; next = None} in
		begin match q.tail with
		| None ->
			q.head <- Some newnode;
			q.tail <- Some newnode
		| Some nt ->
		  nt.next <- Some newnode;
		  q.tail <- Some newnode
	  end
    
  let deq (q : 'a queue) : 'a =
    begin match q.head with
		| None -> failwith "empty queue"
		| Some n ->
			q.head <- n.next;
			if q.head = None then begin q.tail <- None; q.tail <- None end;  
			n.v
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

 


	let length (q: 'a queue) : int = 
		let rec loop (qn : 'a qnode option) : int =
			begin match qn with
			| None -> 0
			| Some n -> 1 + (loop n.next)
			end
		in
			loop q.head



;; run_test "length empty" (fun () ->
	  let q = create () in
		length q = 0)

;; run_test "length" (fun () ->
	  let q = create () in
		enq 1 q;
		enq 2 q;
		length q = 2)


	let to_list (q: 'a queue) : 'q list =
		let rec loop (qn:'a qnode option) (l:'a list) : 'a list =
			begin match qn with
			| None -> List.rev l
			| Some n -> loop n.next (n.v :: l)
			end
		in
		loop q.head [] 

	let print (q: 'a queue) (str_of_a: 'a -> string) : unit =
		let rec loop (qn: 'a qnode option) : unit =
			begin match qn with
			| None -> ()
			| Some n -> print_endline (str_of_a n.v); loop n.next
			end
		in
		print_endline "\n---- q contents ----";
		loop q.head;
		print_endline "------- done -------"

end

(* ------- TEST CASES ------- *)

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


let test () : bool =
	let q : int queue = create () in
	enq 1 q;
	enq 2 q;
	enq 3 q;
	[1;2;3] = to_list q
;; run_test "to_list 1 2 3" test


