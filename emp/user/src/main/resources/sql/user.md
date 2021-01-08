sample
===
* 注释

	select #use("cols")# from user  where  #use("condition")#

cols
===
	id,name,dept_id

updateSample
===
	
	id=#id#,name=#name#,dept_id=#deptId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(deptId)){
	 and dept_id=#deptId#
	@}
	
	