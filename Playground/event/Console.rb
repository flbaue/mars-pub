class Console

	def initialize
		@lock = Mutex.new
	end

	def << text
		#@lock.synchronize {  
			print text
		#}
	end
end