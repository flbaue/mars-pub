class Receiver

	def initialize dispatcher, id, console
		@console = console
		@dispatcher = dispatcher
		@id = id
	end

	def update event
		if !event[:MSG].nil?
			@dispatcher << { :LOG => "RECEIVER#{@id}: update #{event[:MSG]}", :TIME => Time.now }
			@console << "#{@id}>>> #{event[:MSG]}\n"
		end
	end
end