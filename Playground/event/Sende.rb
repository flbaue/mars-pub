class Sender

	def initialize dispatcher
		@dispatcher = dispatcher
	end

	def send message = ""

		if message.empty?
			msg = "Test Message"
		else
			msg = message
		end
		thr = Thread.new do
			@dispatcher << { :MSG => msg, :LOG => "SENDER: send message #{msg}", :TIME => Time.now }
		end
		puts "SENDER ENDE"
		thr.join
	end
end