class Cause < ActiveRecord::Base

  MAX_CAUSES_PER_ORGANISATION = 3

  belongs_to :organisation, :counter_cache => true
  belongs_to :user, :counter_cache => true
  validates_presence_of :name, :organisation_id, :user_id
  validates_uniqueness_of :name, :scope => [:organisation_id, :user_id]

  validate :validate_max

#  named_scope :by_organisation, lambda { |*args|
#    {:conditions => ["released_at > ?", (args.first || 2.weeks.ago)]}
#  }
#
#  named_scope :by_most_tagged, lambda do |*args|

  def validate_max
    errors.add_to_base("You cannot have more than #{MAX_CAUSES_PER_ORGANISATION} causes per organisation.") unless Cause.count(:conditions => ["user_id = ? AND organisation_id = ?", user, organisation]) < MAX_CAUSES_PER_ORGANISATION
  end
end
