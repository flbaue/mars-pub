class Dispatcher

	def initialize receivers = []
		@receivers = receivers
	end

	def add_receiver receiver
		@receivers << receiver
	end

	def << event = {}
		 
		thrs = []
		@receivers.each do |receiver|
			#sleep(0.5)
			thrs << Thread.new {
				receiver.update event
			}
		end
		
		wait_for_threads thrs
	end

private
	
	def wait_for_threads thrs
		thrs.each do |thr|
			thr.join
		end
	end

end