# Starter Script
require_relative './Sende.rb'
require_relative './Dispatcher.rb'
require_relative './Receiver.rb'
require_relative './Logger.rb'
require_relative './Console.rb'

console = Console.new
dispatcher = Dispatcher.new
sender = Sender.new dispatcher
receiver1 = Receiver.new dispatcher, 1, console
receiver2 = Receiver.new dispatcher, 2, console
receiver3 = Receiver.new dispatcher, 3, console
logger = Logger.new console

dispatcher.add_receiver receiver1
dispatcher.add_receiver receiver2
dispatcher.add_receiver receiver3
dispatcher.add_receiver logger

sender.send "Moin Moin"