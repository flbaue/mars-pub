class Logger

	def initialize console
		@console = console
	end


	def update event = {}
		@console << "LOGGER: #{event[:TIME]} #{event[:LOG]}\n" if !event[:LOG].nil?
	end
end